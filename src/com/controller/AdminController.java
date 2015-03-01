package com.controller;

import com.dao.RequestDAO;
import com.dao.UserDAO;
import com.dao.ViewsDAO;
import com.model.Request;
import com.model.User;
import com.model.views.LastRegistration;
import com.model.views.LastRequest;
import com.model.views.LastService;
import com.model.views.ProposeGeneralStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
        return viewsDAO.getLastRegistered("2015-02-01");
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
        return viewsDAO.getLastRequests("2015-02-01");
    }

    @RequestMapping(value = "admin/requests/all")
    @ResponseBody
    public Object allRequests(@RequestParam(value = "limit") int limit,
                              @RequestParam(value = "offset") int offset,
                              @RequestParam(value = "sort", required = false) String sort,
                              @RequestParam(value = "order", required = false) String order,
                              @RequestParam(value = "search", required = false) String search) {
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
        result.total = requestDAO.getRequestRowCount();

        for (final Request request : requestDAO.getAllRequest(limit, offset, sqlOrder)) {
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


    @RequestMapping(value = "admin/services")
    public String services() {
        return "admin\\services";
    }

    @RequestMapping(value = "admin/services/all")
    @ResponseBody
    public List<ProposeGeneralStatistic> getAllServicesStatistic() {
        return viewsDAO.getProposeGeneralStatistic();
    }

    @RequestMapping(value = "admin/servicesTop")
    public String servicesTop() {
        return "admin\\servicesTop";
    }

    @RequestMapping(value = "admin/services/top")
    @ResponseBody
    public List<Map<String, Object>> servicesTopJson() {
        Map<String, Map<String, Object>> result = new LinkedHashMap<>();
        String query = "2015-02-01";
        Set<String> services = new HashSet<>();
        for (LastService ls : viewsDAO.getLastServices(query)) {
            if (!result.containsKey(ls.getDateString())) {
                Map<String, Object> row = new LinkedHashMap<>();
                row.put("date", ls.getDateString());
                row.put(ls.getService(), ls.getCount()+new Random().nextInt(100));
                result.put(ls.getDateString(), row);
            } else {
                result.get(ls.getDateString()).put(ls.getService(), ls.getCount()+new Random().nextInt(100));
            }
            services.add(ls.getService());
        }
        List<Map<String, Object>> result1 = new LinkedList<>();
        for (String key : result.keySet()) {
            result1.add(result.get(key));
        }
        return result1;
    }

}
