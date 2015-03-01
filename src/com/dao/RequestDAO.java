package com.dao;

import com.model.Request;

import java.util.List;

/**
 * Created by Роман on 10.02.2015.
 */
public interface RequestDAO {

    void saveRequest(Request request);

    void updateRequest(Request request);

    void mergeRequest(Request request);

    void removeRequest(Request request);

    Request getRequest(long id);

    List<Request> getAllRequestsByUsername(String username, int offset, int size);

    List<Request> getExecutedRequestListByUsername(String username);

    List<Request> getExecutableRequests();

    List<Request> getAllRequest(int limit ,int offset, String order);

    long getRequestRowCount();
}
