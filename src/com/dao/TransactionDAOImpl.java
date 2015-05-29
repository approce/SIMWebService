package com.dao;

import com.model.Transaction;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("TransactionRepository")
public class TransactionDAOImpl extends HibernateDaoSupport implements TransactionDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public void save(Transaction transaction) {
        getHibernateTemplate().saveOrUpdate(transaction);
        updateUserBalance(transaction);
    }

    private void updateUserBalance(Transaction transaction) {
        String hql = "UPDATE User SET balance=balance + :transaction where id=:id";
        getSessionFactory().getCurrentSession().createQuery(hql)
                           .setParameter("transaction", transaction.getChangeValue())
                           .setParameter("id", transaction.getUser().getId())
                           .executeUpdate();
    }
}
