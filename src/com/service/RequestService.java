package com.service;

import com.model.Request;

import java.util.List;

/**
 * Created by Роман on 10.02.2015.
 */

public interface RequestService {

    void saveRequest(Request request);

    void updateRequest(Request request);

    void mergeRequest(Request request);

    Request getRequest(long id);

    List<Request> getRequestListByUsername(String username);

}
