package com.controller;

import com.dao.RequestDAO;
import com.dao.UserDAO;
import com.dao.ViewsDAO;
import com.model.Request;
import com.model.User;
import com.model.views.LastRegistration;
import com.model.views.LastRequest;
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
 * Created by Роман on 27.02.2015.
 */
@Controller
public class AdminController {
    @Autowired
    private ViewsDAO viewsDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RequestDAO requestDAO;

    @RequestMapping(value = "admin/dashboard")
    public String adminIndex() {
        return "admin\\index";
    }

    @RequestMapping(value = "admin/users")
    public String users() {
        return "admin\\users";
    }

    @RequestMapping(value = "admin/users/last")
    @ResponseBody
    public List<LastRegistration> usersLast() {
        return viewsDAO.getLastRegistered("2015-02-27");
    }

    @RequestMapping(value = "admin/users/all")
    @ResponseBody
    //TODO pagination
    public List<Map<String, Object>> all() {
        List<Map<String, Object>> result = new LinkedList<>();
        for (final User u : userDAO.getUsers()) {
            result.add(new LinkedHashMap<String, Object>() {{
                put("id", u.getId());
                put("username", u.getUsername());
                put("blance", u.getBalance());
                put("requests", u.getRequest().size());
            }});
        }
        return result;
    }

    @RequestMapping(value = "admin/requests")
    public String requests() {
        return "admin\\requests";
    }

    @RequestMapping(value = "admin/requests/last")
    @ResponseBody
    public List<LastRequest> lastRequests() {
        return viewsDAO.getLastRequests("2015-02-20");
    }

    @RequestMapping(value = "admin/requests/all")
    @ResponseBody
    //TODO pagination
    public Object allRequests(@RequestParam(value = "limit") int limit,
                              @RequestParam(value = "offset") int offset) {
        class PaginatedResult {
            private long total;
            private List<Map<String, Object>> rows = new LinkedList<>();

            public long getTotal() {
                return total;
            }

            public void setTotal(long total) {
                this.total = total;
            }

            public List<Map<String, Object>> getRows() {
                return rows;
            }

            public void setRows(List<Map<String, Object>> rows) {
                this.rows = rows;
            }
        }

        PaginatedResult result = new PaginatedResult();
        result.total = requestDAO.getRequestRowCount();

        for (final Request request : requestDAO.getAllRequest(limit, offset)) {
            result.rows.add(new LinkedHashMap<String, Object>() {
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
