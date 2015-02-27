package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Роман on 27.02.2015.
 */
@Controller
public class AdminController {

    @RequestMapping(value="admin")
    public String adminIndex() {
        return "admin\\index";
    }
}
