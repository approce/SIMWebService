package com.dao;

import com.model.Request;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
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
    @Transactional
    public void removeRequest(Request request) {
        getHibernateTemplate().delete(request);
    }


    @Override
    @Transactional(readOnly = true)
    public Request getRequest(long id) {
        return getHibernateTemplate().load(Request.class, id);
    }

    @Override
    @Transactional
    public List<Request> getAllRequestsByUsername(String username, int offset, int size) {
        return (List<Request>) getHibernateTemplate().find("From Request as request where request.user.username=?", username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> getExecutedRequestListByUsername(String username) {
        //return non expired requests by user
        return (List<Request>) getHibernateTemplate()
                .find("From Request as request where request.user.username=? and expired=0", username);
    }

    @Override
    public List<Request> getExecutableRequests() {
        //return from DB all requests which are not expired:
        return (List<Request>) getHibernateTemplate().find("From Request where expired=0");
    }

    @Override
    @Transactional
    public List<Request> getAllRequest(int limit, int offset, String order) {
        String query = "From Request ";
        if (order != null) {
            query += order;
        }
        Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query);
        q.setFirstResult(offset);
        q.setMaxResults(limit);
        return (List<Request>) q.list();
    }

    @Override
    @Transactional
    public long getRequestRowCount() {
        return (long) getSessionFactory().getCurrentSession().createCriteria(Request.class)
                .setProjection(Projections.rowCount()).uniqueResult();
    }
}
