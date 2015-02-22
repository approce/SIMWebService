package com.service;

import com.model.Request;
import com.validation.exceptions.NotEnoughtUserBalance;
import com.validation.exceptions.RequestNotExist;
import org.springframework.web.context.request.async.DeferredResult;


public interface RequestExecutionService {

    //start request execution:
    boolean startRequest(String username, long requestId) throws RequestNotExist, NotEnoughtUserBalance;

    //add request which is currently executable:
    void addExecutableRequst(Request request);

    //get one of executable requests:
    Request getExecutableRequest(long id);

    //set number to one of executable requests:
    boolean setRequestNumber(long id, long number);

    //set suspended getNumber thread for one of executable requests:
    boolean setGetNumberDeferredResult(long requestId, DeferredResult<Long> getNumberDeferredResult);

    //remvoe suspended getNumber thread for one of executable requests:
    boolean removeGetNumberDeferredResult(long requestId);

    //user submit number:
    boolean userSubmitRequestNumber(Request request, boolean submit);

    //set suspended getCode thread for one of executable requests:
    boolean setGetCodeDeferredResult(long requestId, DeferredResult<String> getNumberDeferredResult);

    //delete suspended getCode thread:
    boolean removeGetCodeDeferredResult(long requestId);

    //set code for request:
    boolean setRequestCode(long requestId, String code);

}
