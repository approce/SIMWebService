package com.model.factory;

import com.model.Offer;
import com.model.Request;
import com.model.User;

public class RequestFactory {

    public static Request create(User user, Offer offer) {
        Request request = new Request();
        request.setStatus(Request.STATUS.STOP);
        request.setUser(user);
        request.setOffer(offer);
        return request;
    }
}