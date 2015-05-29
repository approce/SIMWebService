package com.service;

import com.dao.RequestDAO;
import com.model.Request;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

//TODO Delete. (We will use cached DAO instead.
@Service("RequestExecutionPool")
public class RequestExecutionPoolImpl implements RequestExecutionPool {

    private static Logger LOG = Logger.getLogger(RequestExecutionPool.class);

    @Autowired
    private RequestDAO requestDAO;

    @Autowired
    private AsynchronousRequestPoolService asynchronousRequestPoolService;

    //currently execution requests:
    private static List<Request> EXECUTION_REQUESTS_POOL = new LinkedList<>();


    @Override
    public void init() {
        LOG.info("Initialization of RequestExeutionPoolImpl");
        if (EXECUTION_REQUESTS_POOL.size() == 0) {
            EXECUTION_REQUESTS_POOL = requestDAO.getExecutableRequests();
        }
    }

    @Override
    public synchronized void addRequest(Request request) {
        EXECUTION_REQUESTS_POOL.add(request);
    }

    @Override
    public synchronized Request getRequestById(long id) {
        for (Request request : EXECUTION_REQUESTS_POOL) {
            if (request.getId() == id) {
                return request;
            }
        }
        return null;
    }

    @Override
    public synchronized void updateRequest(Request request) {
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
    public synchronized void setRequests(List<Request> requests) {
        EXECUTION_REQUESTS_POOL = requests;
    }

    @Override
    public synchronized List<Request> getRequests() {
        return EXECUTION_REQUESTS_POOL;
    }

    @Override
    public synchronized void removeRequest(long id) {
        EXECUTION_REQUESTS_POOL.remove(id);
    }
}
