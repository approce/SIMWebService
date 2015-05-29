package com.controller;

import com.dao.RequestDAO;
import com.model.Offer;
import com.model.Request;
import com.model.User;
import com.model.factory.RequestFactory;
import com.service.OfferService;
import com.service.RequestService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class RequestGeneralController {

    @Autowired
    private UserService userService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestDAO requestDAO;

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addRequest")
    @ResponseBody
    public ResponseEntity<String> createRequest(Principal principal,
                                                @RequestParam(value = "serviceId") int offerId) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        Offer offer = offerService.getOffer(offerId);
        if(offer == null) {
            return new ResponseEntity<>("wrongService", HttpStatus.BAD_REQUEST);
        }

        Request request = RequestFactory.create(user, offer);
        requestService.saveRequest(request);
        return new ResponseEntity<>(String.valueOf(request.getId()), HttpStatus.OK);
    }


    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "removeRequest")
    @ResponseBody
    public ResponseEntity<String> remove(@RequestParam(value = "id") long id) {
        Request request = requestService.getRequest(id);
        if(request.getStatus().equals(Request.STATUS.STOP)) {
            requestDAO.removeRequest(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
