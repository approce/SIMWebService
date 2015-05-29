package com.service;

import com.dao.TransactionDAO;
import com.model.Request;
import com.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "TransactionService")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDAO transactionDAO;

    @Override
    public Transaction withdrawForRequest(Request request) {
        Transaction transaction = getTransaction(request, true);
        transactionDAO.save(transaction);
        return transaction;
    }

    @Override
    public Transaction drawBackForRequest(Request request) {
        Transaction transaction = getTransaction(request, false);
        transactionDAO.save(transaction);
        return transaction;
    }

    private Transaction getTransaction(Request request, boolean isWithdraw) {
        Transaction transaction = new Transaction();
        transaction.setRequest(request);
        double changevalue = transaction.getChangeValue();
        if(isWithdraw) {
            changevalue = -changevalue;
        }
        transaction.setChangeValue(changevalue);
        return transaction;
    }
}
