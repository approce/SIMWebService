/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.ProposeDAO;
import com.model.Propose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Роман
 */
@Service(value = "ProposeService")
public class ProposeServiceImpl implements ProposeService {

    @Autowired
    private ProposeDAO proposeDAO;

    @Override
    public List<Propose> getProposes() {
        return proposeDAO.getProposes();
    }

    @Override
    public Propose getPropose(int id) {
        return proposeDAO.getPropose(id);
    }

    @Override
    public void setProposes(List<Propose> list) {

    }

}
