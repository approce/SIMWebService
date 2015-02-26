package com.service;

import com.dao.RequestDAO;
import com.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Роман on 22.02.2015.
 */
@Service("RequestExecutionPool")
public class RequestExecutionPoolImpl implements RequestExecutionPool {

    @Autowired
    private RequestDAO requestDAO;

    @Autowired
    private AsynchronousRequestPoolService asynchronousRequestPoolService;

    //currently execution requests:
    private static List<Request> EXECUTION_REQUESTS_POOL = new LinkedList<>();


    @Override
    @PostConstruct
    public void init() {
        if (EXECUTION_REQUESTS_POOL.size() == 0) {
            EXECUTION_REQUESTS_POOL = requestDAO.getExecutableRequests();
        }
    }

    @Override
    public void addRequest(Request request) {
        EXECUTION_REQUESTS_POOL.add(request);
    }

    @Override
    public Request getRequestById(long id) {
        for (Request request : EXECUTION_REQUESTS_POOL) {
            if (request.getId() == id) {
                return request;
            }
        }
        return null;
    }

    @Override
    public void updateRequest(Request request) {
        //if request contains and contains request has not number and updated request has number:
        if (getRequestById(request.getId()) != null &&
                getRequestById(request.getId()).getNumber() == 0 && request.getNumber() != 0) {
            //if somebody already waits- response:
            asynchronousRequestPoolService.setNumberResult(request.getId(), request.getNumber());
        }

        //if request contains and contains request has not code and updated request has number:
        if (getRequestById(request.getId()) != null &&
                getRequestById(request.getId()).getCode() == null && request.getCode() != null) {
            //if somebody already waits- response:
            asynchronousRequestPoolService.setCodeResult(request.getId(), request.getCode());
        }
        if (getRequestById(request.getId()) != null) {
            EXECUTION_REQUESTS_POOL.remove(getRequestById(request.getId()));
        }
        EXECUTION_REQUESTS_POOL.add(request);
        requestDAO.updateRequest(request);
    }


    @Override
    public void setRequests(List<Request> requests) {
        EXECUTION_REQUESTS_POOL = requests;
    }

    @Override
    public List<Request> getRequests() {
        return EXECUTION_REQUESTS_POOL;
    }

    @Override
    public void removeRequest(long id) {
        EXECUTION_REQUESTS_POOL.remove(id);
    }
}
