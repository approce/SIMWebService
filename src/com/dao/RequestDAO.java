package com.dao;

import com.model.Request;

import java.util.List;

/**
 * Created by Роман on 10.02.2015.
 */
public interface RequestDAO {

    void saveRequest(Request request);

    void updateRequest(Request request);

    void removeRequest(Request request);

    Request getRequest(long id);

    List<Request> getExecutableUserRequests(String username);

    List<Request> getExecutableRequests();

    List<Request> getRequests(int limit, int offset, String sort, String order, String username);

    long getRequestRowCount();

    long getUserRequestsRowCount(String username);
}
