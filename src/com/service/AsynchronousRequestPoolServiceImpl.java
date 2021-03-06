package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service("AsynchronousRequestPoolService")
public class AsynchronousRequestPoolServiceImpl implements AsynchronousRequestPoolService {

    @Autowired
    private RequestExecutionPool requestExecutionPool;

    //suspended controllers for number response:
    private static Map<Long, DeferredResult<Long>> REQUEST_NUMBER_MAP = new ConcurrentHashMap<>();

    //suspended controllers for request response:
    private static Map<Long, DeferredResult<String>> REQUEST_CODE_MAP = new ConcurrentHashMap<>();

    @Override
    public synchronized boolean setGetNumberDeferredResult(long requestId, DeferredResult<Long> getNumberDeferredResult) {
        //if already contains:
        if (REQUEST_NUMBER_MAP.containsKey(requestId)) {
            REQUEST_NUMBER_MAP.remove(requestId);
        }
        REQUEST_NUMBER_MAP.put(requestId, getNumberDeferredResult);
        //if number already contains:
        if (requestExecutionPool.getRequestById(requestId).getNumber() != 0) {
            getNumberDeferredResult.setResult(requestExecutionPool.getRequestById(requestId).getNumber());
        }
        return true;
    }

    @Override
    public synchronized boolean removeGetNumberDeferredResult(long requestId) {
        if (REQUEST_NUMBER_MAP.containsKey(requestId)) {
            REQUEST_NUMBER_MAP.remove(requestId);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public synchronized boolean setNumberResult(long requestId, long number) {
        if (REQUEST_NUMBER_MAP.containsKey(requestId)) {
            REQUEST_NUMBER_MAP.get(requestId).setResult(number);
        }
        return true;
    }

    @Override
    public synchronized boolean setGetCodeDeferredResult(long requestId, DeferredResult<String> getCodeDeferredResult) {
        if (requestExecutionPool.getRequestById(requestId) == null) {
            return false;
        }
        //if already contains:
        if (REQUEST_CODE_MAP.containsKey(requestId)) {
            REQUEST_CODE_MAP.remove(requestId);
        }
        REQUEST_CODE_MAP.put(requestId, getCodeDeferredResult);
        //if code already contains:
        if (requestExecutionPool.getRequestById(requestId).getCode() != null) {
            getCodeDeferredResult.setResult(requestExecutionPool.getRequestById(requestId).getCode());
        }
        return true;
    }

    @Override
    public synchronized boolean removeGetCodeDeferredResult(long requestId) {
        if (REQUEST_CODE_MAP.containsKey(requestId)) {
            REQUEST_CODE_MAP.remove(requestId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public synchronized  boolean setCodeResult(long requestId, String message) {
        if (REQUEST_CODE_MAP.containsKey(requestId)) {
            REQUEST_CODE_MAP.get(requestId).setResult(message);
        }
        return true;
    }
}
