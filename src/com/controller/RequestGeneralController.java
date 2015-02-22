package com.controller;

import com.model.Propose;
import com.model.Request;
import com.model.User;
import com.service.ProposeService;
import com.service.RequestService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class RequestGeneralController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProposeService proposeService;

    @Autowired
    private RequestService requestService;

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addRequest")
    public
    @ResponseBody
    Map<String, Object> createRequest(Principal principal, @RequestParam(value = "serviceId") int proposeId) {
        Map<String, Object> result = new LinkedHashMap<>();

        Request request = new Request();
        request.setStatus(Request.STATUS.STOP);
        request.setUser((User) userService.loadUserByUsername(principal.getName()));
        Propose propose = proposeService.getPropose(proposeId);
        if (propose == null) {
            result.put("success", false);
            result.put("error", "wrongService");
        }
        request.setPropose(propose);
        requestService.saveRequest(request);
        result.put("success", true);
        return result;
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/getRequests", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Map<String, Object>> getRequests(Principal principal) {
        List<Map<String, Object>> result = new LinkedList<>();
        List<Request> requests = requestService.getRequestListByUsername(principal.getName());
        for (final Request request : requests) {
            result.add(
                    new LinkedHashMap<String, Object>() {{
                        put("id", request.getId());
                        put("serviceName", request.getPropose().getFullName());
                        put("iconPath", request.getPropose().getIconPath());
                        put("status", request.getStatus());
                        put("number", request.getNumber());
                        put("code", request.getCode());
                    }}
            );
        }
        return result;
    }


}
