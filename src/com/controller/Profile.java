package com.controller;

import com.dao.RequestDAO;
import com.model.Request;
import com.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class Profile {

    @Autowired
    private RequestDAO requestDAO;

    @RequestMapping(value = "requests", method = RequestMethod.GET)
    public String profile() {
        return "requests";
    }

    @RequestMapping(value = "history", method = RequestMethod.GET)
    public String history() {
        return "history";
    }

    @RequestMapping(value = "requestHistory", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> requestHistory(Principal principal) {
        //TODO pagination for request history:
        List<Request> requests = requestDAO.getAllRequestsByUsername(principal.getName(), 0, 0);
        List<Map<String, Object>> result = new LinkedList<>();
        for (Request r : requests) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("id", r.getId());
            row.put("service", r.getPropose().getFullName());
            //get sum of transactions:
            float sum = 0;
            for (Transaction t : r.getTransaction()) {
                sum -= t.getChange_value();
            }
            row.put("price", sum);
            row.put("status", r.getStatus());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yy hh:mm");
            row.put("started", sdf.format(r.getStarted()));
            row.put("number", r.getNumber());
            row.put("code", r.getCode());
            result.add(row);
        }
        return result;
    }

}
