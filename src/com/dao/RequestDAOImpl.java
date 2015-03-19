package com.dao;

import com.model.Request;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    public void removeRequest(Request request) {
        getHibernateTemplate().delete(request);
    }

    @Override
    @Transactional(readOnly = true)
    public Request getRequest(long id) {
        return getHibernateTemplate().load(Request.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> getExecutableUserRequests(String username) {
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
    public List<Request> getRequests(int limit, int offset, String sort, String order, String username) {
        String query = "From Request";
        if (!StringUtils.isEmpty(username)) {
            query += " WHERE user.username= :username";
        }
        query+=" ORDER BY :order";
        if(!StringUtils.isEmpty(order)){
            query+=" DESC";
        }

        Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query);
        if (!StringUtils.isEmpty(username)) {
            q.setParameter("username",username);
        }
        if (!StringUtils.isEmpty(sort)) {
            if (sort.equals("id")) {
                q.setParameter("order","id");
            } else if (sort.equals("username")) {
                q.setParameter("order","user.username");
            } else if (sort.equals("service")) {
                q.setParameter("order","propose.fullName");
            } else if (sort.equals("started")) {
                q.setParameter("order","started");
            } else if (sort.equals("status")) {
                q.setParameter("order","status");
            }
        }else{
            q.setParameter("order","id");
        }
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

    @Override
    @Transactional
    public long getUserRequestsRowCount(String username) {
        return (Long) getSessionFactory().getCurrentSession().createQuery("select count(*) from Request where user.username=?")
                .setParameter(0, username)
                .uniqueResult();
    }
}
