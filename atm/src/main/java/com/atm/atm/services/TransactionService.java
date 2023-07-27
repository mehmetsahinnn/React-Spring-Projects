package com.atm.atm.services;

import com.atm.atm.models.Transactions;
import com.atm.atm.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

}
