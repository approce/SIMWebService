package com.controller;

import com.dao.RequestDAO;
import com.model.Offer;
import com.model.Request;
import com.model.User;
import com.service.OfferService;
import com.service.RequestService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

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
    public
    @ResponseBody
    Map<String, Object> createRequest(Principal principal, @RequestParam(value = "serviceId") int proposeId) {
        Map<String, Object> result = new LinkedHashMap<>();

        Request request = new Request();
        request.setStatus(Request.STATUS.STOP);
        request.setUser((User) userService.loadUserByUsername(principal.getName()));
        Offer offer = offerService.getPropose(proposeId);
        if (offer == null) {
            result.put("success", false);
            result.put("error", "wrongService");
        }
        request.setOffer(offer);
        requestService.saveRequest(request);
        result.put("success", true);
        result.put("id", request.getId());
        return result;
    }



    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "removeRequest")
    @ResponseBody
    public Map<String, Object> remove(@RequestParam(value = "id") long id) {
        Request request = requestService.getRequest(id);
        if (request.getStatus().equals(Request.STATUS.STOP)) {
            //only requests with status STOP can be removed:
            requestDAO.removeRequest(request);
        }

        return new LinkedHashMap<String, Object>() {
            {
                put("success", true);
            }
        };
    }


}
