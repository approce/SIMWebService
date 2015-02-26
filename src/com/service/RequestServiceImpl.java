package com.service;

import com.dao.RequestDAO;
import com.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Роман on 10.02.2015.
 */
@Service(value = "RequestService")
public class RequestServiceImpl implements RequestService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProposeService proposeService;

    @Autowired
    private RequestDAO requestDAO;

    @Override
    public void saveRequest(Request request) {
        requestDAO.saveRequest(request);
    }

    @Override
    public void updateRequest(Request request) {
        requestDAO.updateRequest(request);
    }

    @Override
    public void mergeRequest(Request request) {
        requestDAO.mergeRequest(request);
    }

    @Override
    public Request getRequest(int id) {
        return requestDAO.getRequest(id);
    }

    @Override
    public List<Request> getRequestListByUsername(String username) {
        return requestDAO.getExecutedRequestListByUsername(username);
    }


}
