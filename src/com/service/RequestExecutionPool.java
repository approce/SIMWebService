package com.service;

import com.model.Request;

import java.util.List;

/**
 * Created by Роман on 22.02.2015.
 */
public interface RequestExecutionPool {

    void init();

    void setRequests(List<Request> requests);

    List<Request> getRequests();

    void addRequest(Request request);

    Request getRequestById(long id);

    void updateRequest(Request request);

    void removeRequest(long id);
}
