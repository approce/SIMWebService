package com.controller.adminControllers;

import com.dao.RequestDAO;
import com.dao.UserDAO;
import com.dao.ViewsDAO;
import com.model.Request;
import com.model.Transaction;
import com.model.User;
import com.model.views.LastRegistration;
import com.utils.PaginatedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Роман on 02.03.2015.
 */
@Controller
public class AdminUsers {

    @Autowired
    private ViewsDAO viewsDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RequestDAO requestDAO;

    @RequestMapping(value = "admin/users")
    public String users() {
        return "admin\\users";
    }

    @RequestMapping(value = "admin/users", params = "username")
    public String user(Model model, @RequestParam(value = "username") String username) {
        model.addAttribute("user", userDAO.getUser(username));
        return "admin\\user";
    }

    @RequestMapping(value = "admin/users/requests")
    public
    @ResponseBody
    PaginatedResult userRequests(
            @RequestParam(value = "limit") int limit,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "offset") int offset,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "order", required = false) String order) {

        PaginatedResult result = new PaginatedResult();
        result.setTotal(requestDAO.getUserRequestsRowCount(username));

        for (final Request request : requestDAO.getRequests(limit, offset, sort, order, username)) {
            result.getRows().add(new LinkedHashMap<String, Object>() {
                {
                    put("id", request.getId());
                    put("service", request.getPropose().getFullName());
                    put("started", request.getStarted());
                    put("status", request.getStatus());
                    //get sum of transactions:
                    float sum = 0;
                    for (Transaction t : request.getTransaction()) {
                        sum -= t.getChange_value();
                    }
                    put("transactions", sum);
                }
            });
        }
        return result;
    }


    @RequestMapping(value = "admin/users/last")
    @ResponseBody
    public List<LastRegistration> usersLast(@RequestParam(value = "from") String from) {
        return viewsDAO.getLastRegistered(from);
    }


    @RequestMapping(value = "admin/users/all")
    @ResponseBody
    public PaginatedResult all(@RequestParam(value = "limit") int limit,
                               @RequestParam(value = "offset") int offset,
                               @RequestParam(value = "sort", required = false) String sort,
                               @RequestParam(value = "order", required = false) String order,
                               @RequestParam(value = "search", required = false) String search) {
        String query = null;
        if (sort != null) {
            query = "order by";
            if (sort.equals("id")) {
                query += " id";
            } else if (sort.equals("username")) {
                query += " username";
            } else if (sort.equals("balance")) {
                query += " balance";
            }
            if (order.equals("desc")) {
                query += " DESC";
            }
        }
        if (search != null) {
            if (query == null) {
                query = new String();
            }
            query += "WHERE username LIKE '%" + search + "%'";
        }

        PaginatedResult result = new PaginatedResult();
        result.setTotal(userDAO.getUserCount());
        List<User> users = userDAO.getUsers(limit, offset, query);
        for (final User u : users) {
            result.getRows().add(new LinkedHashMap<String, Object>() {{
                put("id", u.getId());
                put("username", u.getUsername());
                put("balance", u.getBalance());
                put("requests", u.getRequest().size());
            }});
        }
        return result;
    }
}
