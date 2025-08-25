package org.example.backend.service;

import org.example.backend.model.Product;
import org.example.backend.model.Transaction;
import org.example.backend.model.TransactionType;
import org.example.backend.model.User;

public interface ITransactionService {
    void execute(Transaction transaction);
}
