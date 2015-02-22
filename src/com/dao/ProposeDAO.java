/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Propose;

import java.util.List;

/**
 * @author Роман
 */
public interface ProposeDAO {

    List<Propose> getProposes();

    Propose getPropose(int id);

    void setProposes(List<Propose> list);
}
