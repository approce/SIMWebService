package com.service;

import com.model.Request;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service(value = "GSMService")
public class GSMServiceImpl implements GSMService {

    private static Logger LOG = Logger.getLogger(GSMServiceImpl.class);

    @Autowired
    private RequestExecutionService requestExecutionService;

    @Autowired
    private RequestExecutionPool requestExecutionPool;

    @Override
    //@PostConstruct
    public void init() {
        LOG.info("Initialization of GSMServiceImpl");
        //go throw all requests in requestExecutePool and start request for each of them:
        for (Request request : requestExecutionPool.getRequests()) {
            if (request.getStatus().equals(Request.STATUS.WAIT_NUMBER)) {
                //TODO create priority for reuquest:
                startGetRequestNumber(request.getId(), request.getPropose().getId(), 10);
            }
            if (request.getStatus().equals(Request.STATUS.WAIT_CODE)) {
                startGetRequestCode(request.getId());
            }
        }
    }

    @Override
    public boolean startGetRequestNumber(final long requestId, long proposeId, float priority) {
        //set Number after few seconds:
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000 + new Random().nextInt(2000));
                    //set number:
                    int random = new Random().nextInt(9999999);
                    requestExecutionService.setRequestNumber(requestId, 380990000000l + random);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return true;

    }

    @Override
    public boolean submitRequestNumber(final long requestId, boolean submit) {
        //set code after few seconds:
        if (submit == false) {
            return true;
        }
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000 + new Random().nextInt(2000));
                    //set number:
                    int random = new Random().nextInt(8999);
                    String code = String.valueOf(1000 + random);
                    requestExecutionService.setRequestCode(requestId, code);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return true;
    }

    @Override
    public boolean startGetRequestCode(final long requestId) {
        //set code after few seconds:
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000 + new Random().nextInt(2000));
                    //set number:
                    int random = new Random().nextInt(8999);
                    String code = String.valueOf(1000 + random);
                    requestExecutionService.setRequestCode(requestId, code);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return true;
    }
}
