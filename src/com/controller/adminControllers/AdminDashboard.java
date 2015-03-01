package com.controller.adminControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Роман on 02.03.2015.
 */
@Controller
public class AdminDashboard {

    @RequestMapping(value = "admin/dashboard")
    public String adminIndex() {
        return "admin\\index";
    }

}
