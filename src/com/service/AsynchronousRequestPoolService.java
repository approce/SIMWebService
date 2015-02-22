package com.service;

import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by Роман on 22.02.2015.
 */
public interface AsynchronousRequestPoolService {
    //set suspended getNumber thread for one of executable requests:
    boolean setGetNumberDeferredResult(long requestId, DeferredResult<Long> getNumberDeferredResult);

    //remvoe suspended getNumber thread for one of executable requests:
    boolean removeGetNumberDeferredResult(long requestId);

    //if number received- set it to deferredResult:
    boolean setNumberResult(long requestId, long number);

    //set suspended getCode thread for one of executable requests:
    boolean setGetCodeDeferredResult(long requestId, DeferredResult<String> getNumberDeferredResult);

    //delete suspended getCode thread:
    boolean removeGetCodeDeferredResult(long requestId);

    //if code received- set it to deferredResult:
    boolean setCodeResult(long requestId, String message);


}
