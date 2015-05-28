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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Роман
 */
@Controller
public class Offers {

    @Autowired
    private OfferService offerService;

    @RequestMapping(value = "getServices")
    @ResponseBody
    public List<Offer> getProposes() {
        return offerService.getOffers();
    }

}
