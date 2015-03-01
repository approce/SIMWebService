package com.controller.adminControllers;

import com.dao.RequestDAO;
import com.dao.ViewsDAO;
import com.model.Request;
import com.model.views.LastRequest;
import com.utils.PaginatedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Роман on 02.03.2015.
 */
@Controller
public class AdminRequests {

    @Autowired
    private ViewsDAO viewsDAO;

    @Autowired
    private RequestDAO requestDAO;

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
    public Object allRequests(@RequestParam(value = "limit") int limit,
                              @RequestParam(value = "offset") int offset,
                              @RequestParam(value = "sort", required = false) String sort,
                              @RequestParam(value = "order", required = false) String order,
                              @RequestParam(value = "search", required = false) String search) {


        String sqlOrder = null;
        if (sort != null) {
            sqlOrder = "order by";
            if (sort.equals("id")) {
                sqlOrder += " id";
            } else if (sort.equals("username")) {
                sqlOrder += " user.username";
            } else if (sort.equals("service")) {
                sqlOrder += " propose.fullName";
            } else if (sort.equals("started")) {
                sqlOrder += " started";
            } else if (sort.equals("status")) {
                sqlOrder += " status";
            }
            if (order.equals("desc")) {
                sqlOrder += " DESC";
            }
        }
        if (search != null) {
            if (sqlOrder == null) {
                sqlOrder = new String();
            }
            sqlOrder += "WHERE user.username LIKE '%" + search + "%'";
        }


        PaginatedResult result = new PaginatedResult();
        result.setTotal(requestDAO.getRequestRowCount());

        for (final Request request : requestDAO.getAllRequest(limit, offset, sqlOrder)) {
            result.getRows().add(new LinkedHashMap<String, Object>() {
                {
                    put("id", request.getId());
                    put("service", request.getPropose().getFullName());
                    put("username", request.getUser().getUsername());
                    put("started", request.getStarted());
                    put("status", request.getStatus());
                }
            });
        }
        return result;
    }

}
