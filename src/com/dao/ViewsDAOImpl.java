package com.dao;

import com.model.views.LastRegistration;
import com.model.views.LastRequest;
import com.model.views.LastService;
import com.model.views.ProposeGeneralStatistic;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Роман on 28.02.2015.
 */
@Repository
public class ViewsDAOImpl extends HibernateDaoSupport implements ViewsDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public List<LastRegistration> getLastRegistered(String query) {
        List<LastRegistration> result = (List<LastRegistration>) getHibernateTemplate()
                .find("from LastRegistration where registered>?", query);
        return result;
    }

    @Override
    public List<LastRequest> getLastRequests(String query) {
        List<LastRequest> result = (List<LastRequest>) getHibernateTemplate()
                .find("from LastRequest where finished>?", query);
        return result;
    }

    @Override
    public List<ProposeGeneralStatistic> getProposeGeneralStatistic() {
        List<ProposeGeneralStatistic> result = (List<ProposeGeneralStatistic>) getHibernateTemplate()
                .find("from ProposeGeneralStatistic");
        return result;
    }

    @Override
    public List<LastService> getLastServices(String query) {
        List<LastService> result = (List<LastService>) getHibernateTemplate()
                .find("from LastService where started>?", query);
        return result;
    }

}
