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

    Request getRequest(int id);

    List<Request> getRequestListByUsername(String username);

    List<Request> getExecutableRequests();


}
