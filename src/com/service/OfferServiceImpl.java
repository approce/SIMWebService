/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.OfferDAO;
import com.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Роман
 */
@Service(value = "ProposeService")
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferDAO offerDAO;

    @Override
    public List<Offer> getProposes() {
        return offerDAO.getProposes();
    }

    @Override
    public Offer getPropose(int id) {
        return offerDAO.getPropose(id);
    }

    @Override
    public void setProposes(List<Offer> list) {

    }

}
