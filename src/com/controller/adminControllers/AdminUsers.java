package com.controller.adminControllers;

import com.dao.UserDAO;
import com.dao.ViewsDAO;
import com.model.User;
import com.model.views.LastRegistration;
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
public class AdminUsers {

    @Autowired
    private ViewsDAO viewsDAO;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "admin/users")
    public String users() {
        return "admin\\users";
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
