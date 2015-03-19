package com.controller;

import com.dao.ProposeDAO;
import com.dao.RequestDAO;
import com.model.Request;
import com.model.Transaction;
import com.utils.PaginatedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/profile")
public class Profile {

    @Autowired
    private RequestDAO requestDAO;

    @Autowired
    private ProposeDAO proposeDAO;

    @RequestMapping(value = "/requests")
    public String profile(Model model) {
        //set proposes:
        //TODO delete this and get proposed from UI:
        model.addAttribute("proposes", proposeDAO.getProposes());
        return "requests";
    }

    @RequestMapping(value = "history")
    public String history() {
        return "history";
    }


    @RequestMapping(value = "history/show")
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
                    put("service", request.getPropose().getFullName());
                    put("username", request.getUser().getUsername());
                    put("started", request.getStarted());
                    put("status", request.getStatus());

                    //get sum of transactions:
                    float sum = 0;
                    for (Transaction t : request.getTransaction()) {
                        sum -= t.getChange_value();
                    }
                    put("price", sum);
                    put("number", request.getNumber());
                    put("code", request.getCode());
                }
            });
        }
        return result;
    }
}
