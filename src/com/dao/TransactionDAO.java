package com.dao;

import com.model.Transaction;

/**
 * Created by Роман on 15.02.2015.
 */
public interface TransactionDAO {

    void save(Transaction transaction);
}
