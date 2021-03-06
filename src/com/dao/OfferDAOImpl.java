/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Offer;
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
public class OfferDAOImpl extends HibernateDaoSupport implements OfferDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Offer> getProposes() {
        List<Offer> result = (List<Offer>) getHibernateTemplate().find("from Offer");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Offer getPropose(int id) {
        List<Offer> result = (List<Offer>) getHibernateTemplate().find("from Offer where id=?", id);
        return result.size() == 0 ? null : result.get(0);
    }
}
