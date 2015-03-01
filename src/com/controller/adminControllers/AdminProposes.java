package com.controller.adminControllers;

import com.dao.ViewsDAO;
import com.model.views.LastService;
import com.model.views.ProposeGeneralStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Роман on 02.03.2015.
 */
@Controller
public class AdminProposes {

    @Autowired
    private ViewsDAO viewsDAO;

    @RequestMapping(value = "admin/services")
    public String services() {
        return "admin\\services";
    }

    @RequestMapping(value = "admin/servicesTop")
    public String servicesTop() {
        return "admin\\servicesTop";
    }


    @RequestMapping(value = "admin/services/all")
    @ResponseBody
    public List<ProposeGeneralStatistic> getAllServicesStatistic() {
        return viewsDAO.getProposeGeneralStatistic();
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
                row.put(ls.getService(), ls.getCount() + new Random().nextInt(100));
                result.put(ls.getDateString(), row);
            } else {
                result.get(ls.getDateString()).put(ls.getService(), ls.getCount() + new Random().nextInt(100));
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
