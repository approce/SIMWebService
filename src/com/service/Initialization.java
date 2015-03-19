package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Роман on 01.03.2015.
 */
@Component
public class Initialization implements ApplicationListener<org.springframework.context.event.ContextRefreshedEvent> {

    @Autowired
    private GSMService gsmService;

    @Autowired
    private RequestExecutionPool requestExecutionPool;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getDisplayName().contains("dispatcher-servlet")) {
            return;
        }
        requestExecutionPool.init();
        gsmService.init();
    }
}
