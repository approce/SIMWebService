package com.controller.adminControllers;

import com.dao.RequestDAO;
import com.dao.ViewsDAO;
import com.model.Request;
import com.model.Transaction;
import com.model.views.LastRequest;
import com.service.RequestExecutionPool;
import com.utils.PaginatedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Роман on 02.03.2015.
 */
@Controller
public class AdminRequests {

    @Autowired
    private ViewsDAO viewsDAO;

    @Autowired
    private RequestDAO requestDAO;

    @Autowired
    private RequestExecutionPool requestExecutionPool;

    @RequestMapping(value = "admin/requests")
    public String requests() {
        return "admin\\requests";
    }


    @RequestMapping(value = "admin/requests/last")
    @ResponseBody
    public List<LastRequest> lastRequests() {
        return viewsDAO.getLastRequests("2015-02-01");
    }

    @RequestMapping(value = "admin/requests/all")
    @ResponseBody
    public PaginatedResult allRequests(@RequestParam(value = "limit") int limit,
                                       @RequestParam(value = "offset") int offset,
                                       @RequestParam(value = "sort", required = false) String sort,
                                       @RequestParam(value = "order", required = false) String order,
                                       @RequestParam(value = "search", required = false) String search) {
        PaginatedResult result = new PaginatedResult();
        result.setTotal(requestDAO.getRequestRowCount());

        for (final Request request : requestDAO.getRequests(limit, offset, sort, order, null)) {
            result.getRows().add(new LinkedHashMap<String, Object>() {
                {
                    put("id", request.getId());
                    put("service", request.getOffer().getFullName());
                    put("username", request.getUser().getUsername());
                    put("started", request.getStarted());
                    put("status", request.getStatus());
                }
            });
        }
        return result;
    }

    @RequestMapping(value = "admin/requestsPool")
    public String getPool() {
        return "admin\\requestsPool";
    }


    @RequestMapping(value = "admin/requests/pool")
    @ResponseBody
    List<Map<String, Object>> getPoolRequests() {
        List<Map<String, Object>> result = new LinkedList<>();

        int index = 0;
        for (Request r : requestExecutionPool.getRequests()) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("index", index);
            row.put("id", r.getId());
            row.put("service", r.getOffer().getFullName());
            row.put("username", r.getUser().getUsername());
            List<Double> transactions = new LinkedList<>();
            if (r.getTransaction() != null) {
                for (Transaction t : r.getTransaction()) {
                    transactions.add(t.getChangeValue());
                }
            }
            row.put("transactions", transactions);
            row.put("status", r.getStatus());
            result.add(row);
            index++;
        }
        return result;
    }

}
