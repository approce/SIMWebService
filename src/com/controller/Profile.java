package com.controller;

import com.dao.OfferDAO;
import com.dao.RequestDAO;
import com.model.Request;
import com.service.RequestService;
import com.utils.PaginatedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/profile")
public class Profile {

    @Autowired
    private RequestDAO requestDAO;

    @Autowired
    private OfferDAO offerDAO;

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "requests/executable")
    public String profile(Model model) {
        //set proposes:
        //TODO delete this and get proposed from UI:
        model.addAttribute("proposes", offerDAO.getProposes());
        return "requests";
    }

    @RequestMapping(value = "requests/executable", params = "data")
    @ResponseBody
    public List<Map<String, Object>> getRequests(Principal principal) {
        System.out.println("i am hre");
        List<Map<String, Object>> result = new LinkedList<>();
        List<Request> requests = requestService.getRequestListByUsername(principal.getName());
        for (final Request request : requests) {
            result.add(
                    new LinkedHashMap<String, Object>() {{
                        put("id", request.getId());
                        put("serviceName", request.getOffer().getFullName());
                        put("iconPath", request.getOffer().getIconPath());
                        put("status", request.getStatus());
                        put("number", request.getNumber());
                        put("code", request.getCode());
                    }}
            );
        }
        return result;
    }

    @RequestMapping(value = "requests/history")
    public String history() {
        return "history";
    }

    @RequestMapping(value = "requests/history", params = "data")
    @ResponseBody
    public PaginatedResult requestsHistory(@RequestParam(value = "limit") int limit,
                                           @RequestParam(value = "offset") int offset,
                                           @RequestParam(value = "sort", required = false) String sort,
                                           @RequestParam(value = "order", required = false) String order,
                                           Principal principal) {
        PaginatedResult result = new PaginatedResult();
        result.setTotal(requestDAO.getUserRequestsRowCount(principal.getName()));
        for (final Request request : requestDAO.getRequests(limit, offset, sort, order, principal.getName())) {
            result.getRows().add(new LinkedHashMap<String, Object>() {
                {
                    put("id", request.getId());
                    put("service", request.getOffer().getFullName());
                    put("username", request.getUser().getUsername());
                    put("started", request.getStarted());
                    put("status", request.getStatus());
                    put("price", request.getTransactionSum());
                    put("number", request.getNumber());
                    put("code", request.getCode());
                }
            });
        }
        return result;
    }
}
