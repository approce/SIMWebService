/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Offer;

import java.util.List;

/**
 * @author Роман
 */
public interface OfferService {

    List<Offer> getOffers();

    Offer getOffer(int id);

    void setOffers(List<Offer> list);

}
