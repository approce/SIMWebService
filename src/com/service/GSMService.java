package com.service;


public interface GSMService {

    void init();

    boolean startGetRequestNumber(long requestId, String offerId, float priority);

    boolean submitRequestNumber(long requeistId, boolean submit);

    boolean startGetRequestCode(long requestId);
}
