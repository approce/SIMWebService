package com.service;


public interface GSMService {

    void init();

    boolean startGetRequestNumber(long requestId, long proposeId, float priority);

    boolean submitRequestNumber(long requeistId, boolean submit);

    boolean startGetRequestCode(long requestId);
}
