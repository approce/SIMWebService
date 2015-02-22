package com.service;

import com.dao.RequestDAO;
import com.model.Request;
import com.model.Transaction;
import com.model.User;
import com.validation.exceptions.NotEnoughtUserBalance;
import com.validation.exceptions.RequestNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Роман on 17.02.2015.
 */

@Service(value = "RequestExecutionService")
public class RequestExecutionServiceImpl implements RequestExecutionService {

    @Autowired
    private UserService userService;

    @Autowired
    private GSMService gsmService;

    @Autowired
    private RequestDAO requestDAO;

    @Autowired
    private TransactionService transactionService;

    //currently execution requests:
    private static List<Request> EXECUTION_REQUESTS_POOL = new LinkedList<>();

    //suspended controllers for number response:
    private static Map<Long, DeferredResult<Long>> REQUEST_NUMBER_MAP = new LinkedHashMap<>();

    //suspended controllers for request response:
    private static Map<Long, DeferredResult<String>> REQUEST_CODE_MAP = new LinkedHashMap<>();

    @Override
    public void addExecutableRequst(Request request) {
        EXECUTION_REQUESTS_POOL.add(request);
    }

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
        //if request exist and money is enought:
        //create new transaction:
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setChange_value(-request.getPropose().getPrice());
        //save transaction:
        transactionService.save(transaction);
        //send request to pool:
        gsmService.startGetRequestNumber(request.getId(), request.getPropose().getId(), 10);
        //set request status:
        request.setStatus(Request.STATUS.WAIT_FOR_NUMBER);
        requestDAO.updateRequest(request);

        //add to executable:
        addExecutableRequst(request);
        return true;
    }

    @Override
    public Request getExecutableRequest(long id) {
        //TODO: create initialization of requests on server up.
        //init kostil:
        if (EXECUTION_REQUESTS_POOL.size() == 0) {
            EXECUTION_REQUESTS_POOL = requestDAO.getExecutableRequests();
        }
        for (Request request : EXECUTION_REQUESTS_POOL) {
            if (request.getId() == id) {
                return request;
            }
        }
        return null;
    }

    @Override
    public boolean setRequestNumber(long id, long number) {
        //check if Request exist:
        if (getExecutableRequest(id) == null) {
            return false;
        }
        //set number to request:
        getExecutableRequest(id).setNumber(number);
        //set status:
        getExecutableRequest(id).setStatus(Request.STATUS.WAIT_FOR_NUMBER_SUBMIT);
        //update request in db:
        requestDAO.updateRequest(getExecutableRequest(id));
        //if smbd already waits for this number:
        if (REQUEST_NUMBER_MAP.containsKey(id)) {
            REQUEST_NUMBER_MAP.get(id).setResult(number);
        }
        return true;
    }


    @Override
    public boolean setGetNumberDeferredResult(long requestId, DeferredResult<Long> getNumberDeferredResult) {
        if (getExecutableRequest(requestId) == null) {
            return false;
        }
        //if already contains:
        if (REQUEST_NUMBER_MAP.containsKey(requestId)) {
            REQUEST_NUMBER_MAP.remove(requestId);
        }
        REQUEST_NUMBER_MAP.put(requestId, getNumberDeferredResult);
        //if number already contains:
        if (getExecutableRequest(requestId).getNumber() != 0) {
            getNumberDeferredResult.setResult(getExecutableRequest(requestId).getNumber());
        }
        return true;
    }

    @Override
    public boolean removeGetNumberDeferredResult(long requestId) {
        if (REQUEST_NUMBER_MAP.containsKey(requestId)) {
            REQUEST_NUMBER_MAP.remove(requestId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean userSubmitRequestNumber(Request request, boolean submit) {
        if (submit == true) {
            gsmService.submitRequestNumber(request.getId(), submit);
            //set status:
            request.setStatus(Request.STATUS.WAIT_FOR_CODE);
            requestDAO.updateRequest(request);
        }
        return true;
    }

    @Override
    public boolean setGetCodeDeferredResult(long requestId, DeferredResult<String> getCodeDeferredResult) {
        if (getExecutableRequest(requestId) == null) {
            return false;
        }
        //if already contains:
        if (REQUEST_CODE_MAP.containsKey(requestId)) {
            REQUEST_CODE_MAP.remove(requestId);
        }
        REQUEST_CODE_MAP.put(requestId, getCodeDeferredResult);
        //if number already contains:
        if (getExecutableRequest(requestId).getCode() != null) {
            getCodeDeferredResult.setResult(getExecutableRequest(requestId).getCode());
        }
        return true;

    }

    @Override
    public boolean removeGetCodeDeferredResult(long requestId) {
        if (REQUEST_CODE_MAP.containsKey(requestId)) {
            REQUEST_CODE_MAP.remove(requestId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean setRequestCode(long id, String code) {
        //check if Request exist:
        if (getExecutableRequest(id) == null) {
            return false;
        }
        //set number to request:
        getExecutableRequest(id).setCode(code);
        //set status:
        getExecutableRequest(id).setStatus(Request.STATUS.COMPLETED);
        //update request in db:
        requestDAO.updateRequest(getExecutableRequest(id));
        //if smbd already waits for this number:
        if (REQUEST_CODE_MAP.containsKey(id)) {
            REQUEST_CODE_MAP.get(id).setResult(code);
        }
        return true;
    }
}
