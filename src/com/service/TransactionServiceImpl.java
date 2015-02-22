package com.service;

import com.dao.TransactionDAO;
import com.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "TransactionService")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDAO transactionDAO;

    @Override
    public void save(Transaction transaction) {
        transactionDAO.save(transaction);
    }
}
