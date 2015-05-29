package com.service;

import com.model.Request;
import com.model.Transaction;

public interface TransactionService {

    Transaction withdrawForRequest(Request request);

    Transaction drawBackForRequest(Request request);
}
