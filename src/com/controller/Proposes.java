/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Propose;
import com.service.ProposeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Роман
 */
@Controller
public class Proposes {

    @Autowired
    private ProposeService proposeService;

    @RequestMapping(value = "/services")
    public String getProposesList(Model model) {
        List<Propose> proposes = proposeService.getProposes();
        model.addAttribute("services", proposes);
        return "services";
    }


    @RequestMapping(value = "getServices")
    @ResponseBody
    public List<Propose> getProposes() {
        return proposeService.getProposes();
    }

}
