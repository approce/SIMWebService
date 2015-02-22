/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Propose;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Роман
 */
@Repository("ProposeRepository")
public class ProposeDAOImpl extends HibernateDaoSupport implements ProposeDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Propose> getProposes() {
        List<Propose> result = (List<Propose>) getHibernateTemplate().find("from Propose");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Propose getPropose(int id) {
        List<Propose> result = (List<Propose>) getHibernateTemplate().find("from Propose where id=?", id);
        return result.size() == 0 ? null : result.get(0);
    }

    @Override
    public void setProposes(List<Propose> list) {
    }

}
