package com.dao;

import com.model.views.LastRegistration;
import com.model.views.LastRequest;

import java.util.List;

/**
 * Created by Роман on 28.02.2015.
 */
public interface ViewsDAO {
    List<LastRegistration> getLastRegistered(String query);

    List<LastRequest> getLastRequests(String query);
}
