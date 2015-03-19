package com.service;

import com.validation.exceptions.NotEnoughtUserBalance;
import com.validation.exceptions.RequestNotExist;


public interface RequestExecutionService {

    //start request execution:
    boolean startRequest(String username, long requestId) throws RequestNotExist, NotEnoughtUserBalance;

    //set number to one of executable requests:
    boolean setRequestNumber(long id, long number);

    //user submit number:
    boolean userSubmitRequestNumber(String username, long id, boolean submit);

    //set code for request:
    boolean setRequestCode(long requestId, String code);

    //finish request:
    boolean finishRequest(long requestId);

}
