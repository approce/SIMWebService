package com.dao;

import com.model.Request;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Роман on 10.02.2015.
 */
@Repository("ProposeRequestRepository")
public class RequestDAOImpl extends HibernateDaoSupport implements RequestDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }


    @Override
    @Transactional
    public void saveRequest(Request request) {
        getHibernateTemplate().save(request);
    }

    @Override
    @Transactional
    public void updateRequest(Request request) {
        getHibernateTemplate().update(request);
    }

    @Override
    @Transactional
    public void mergeRequest(Request request) {
        getHibernateTemplate().merge(request);
    }

    @Override
    @Transactional(readOnly = true)
    public Request getRequest(int id) {
        return getHibernateTemplate().load(Request.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> getRequestListByUsername(String username) {
        return (List<Request>) getHibernateTemplate()
                .find("From Request as request where request.user.username=?", username);
    }

    @Override
    public List<Request> getExecutableRequests() {
        return (List<Request>) getHibernateTemplate().find("From Request where expired=0 and status!='STOP'");
    }
}
