/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Offer;
import com.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class Index {

    @Autowired
    private OfferService offerService;

    @RequestMapping(value = "/")
    public String offers(Model model) {
        List<Offer> offers = offerService.getOffers();
        model.addAttribute("services", offers);
        return "index";
    }

}
