/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Роман
 */
@Repository("UserRepository")
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public void saveUser(User u) {
        getHibernateTemplate().saveOrUpdate(u);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(String username) {
        List<User> result = (List<User>) getHibernateTemplate().find("from User where username=?", username);
        return result.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserName(String username) {
        List<User> result = (List<User>) getHibernateTemplate().find("from User where username=?", username);
        return result.size() > 0;
    }

}
