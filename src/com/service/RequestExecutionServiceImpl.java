package com.service;

import com.model.Request;
import com.model.Transaction;
import com.model.User;
import com.validation.exceptions.NotEnoughtUserBalance;
import com.validation.exceptions.RequestNotExist;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Роман on 17.02.2015.
 */

@Service(value = "RequestExecutionService")
public class RequestExecutionServiceImpl implements RequestExecutionService {

    private static Logger LOG = Logger.getLogger(RequestExecutionServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private GSMService gsmService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private RequestExecutionPool requestExecutionPool;

    @Override
    public boolean startRequest(String username, long requestId) throws RequestNotExist, NotEnoughtUserBalance {
        User user = (User) userService.loadUserByUsername(username);
        Request request = null;
        //check if request exist:
        if ((request = user.getRequest(requestId)) == null) {
            throw new RequestNotExist();
        }
        //check if money is enought:
        if (!(user.getBalance() >= request.getPropose().getPrice())) {
            throw new NotEnoughtUserBalance();
        }
        //create new transaction:
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setChange_value(-request.getPropose().getPrice());

        //save transaction to db:
        transactionService.save(transaction);

        //save transaction to request:
        request.addTransaction(transaction);

        //send request to gsm pool:
        gsmService.startGetRequestNumber(request.getId(), request.getPropose().getId(), 10);
        //set request status:
        request.setStatus(Request.STATUS.WAIT_NUMBER);
        request.setStarted(new Date(System.currentTimeMillis()));
        //add to executable:
        requestExecutionPool.addRequest(request);
        requestExecutionPool.updateRequest(request);
        return true;
    }

    @Override
    public boolean setRequestNumber(long id, long number) {
        LOG.info("set number for request id=" + id);
        Request request = requestExecutionPool.getRequestById(id);
        if (request == null) {
            return false;
        }
        //set number to request:
        request.setNumber(number);
        //set status:
        request.setStatus(Request.STATUS.NUMBER_SUBMIT);
        //update in pool:
        requestExecutionPool.updateRequest(request);
        return true;
    }


    @Override
    public boolean userSubmitRequestNumber(String username, long id, boolean submit) {
        Request request = requestExecutionPool.getRequestById(id);
        if (request == null) {
            return false;
        }
        gsmService.submitRequestNumber(request.getId(), submit);
        if (submit == true) {
            request.setStatus(Request.STATUS.WAIT_CODE);
        } else {
            User user = (User) userService.loadUserByUsername(username);
            //create plus transaction:
            Transaction transaction = new Transaction();
            transaction.setUser(user);
            transaction.setChange_value(-request.getTransaction().get(0).getChange_value());
            transactionService.save(transaction);
            request.addTransaction(transaction);
            request.setStatus(Request.STATUS.NUMBER_REJECT);
        }
        requestExecutionPool.updateRequest(request);
        return true;
    }


    @Override
    public boolean setRequestCode(long id, String code) {
        LOG.info("set code for request id=" + id);
        Request request = requestExecutionPool.getRequestById(id);
        if (request == null) {
            return false;
        }
        //set code to request:
        request.setCode(code);
        //set status:
        request.setStatus(Request.STATUS.COMPLETED);
        //update in pool:
        requestExecutionPool.updateRequest(request);
        return true;
    }

    @Override
    public boolean finishRequest(long requestId) {
        Request finishedRequest = requestExecutionPool.getRequestById(requestId);
        finishedRequest.setFinished(new Date(System.currentTimeMillis()));
        finishedRequest.setExpired(true);
        //update in pool:
        requestExecutionPool.updateRequest(finishedRequest);
        requestExecutionPool.removeRequest(requestId);
        return true;
    }
}
