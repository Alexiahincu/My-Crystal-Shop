package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.Transaction;
import org.example.backend.model.TransactionType;
import org.example.backend.repository.IProductRepository;
import org.example.backend.repository.ITransactionRepository;
import org.example.backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionService {
    @Autowired
    ITransactionRepository transactionRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IProductRepository productRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByUserId(String userId) {
        if(!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist.");
        }

        return transactionRepository.findByUserId(userId);
    }

    public List<Transaction> getTransactionsByProductId(String productId) {
        if(!productRepository.existsById(productId)) {
            throw new IllegalArgumentException("Product with id " + productId + " does not exist.");
        }

        return transactionRepository.findByProductId(productId);
    }

    public List<Transaction> getTransactionsByType(TransactionType type) {
        return transactionRepository.findByTransactionType(type);
    }

    public List<Transaction> getTransactionsByPeriod(LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("Data de sfârșit trebuie să fie după data de început.");
        }

        if (end.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data de sfârșit nu poate fi în viitor.");
        }

        return transactionRepository.findByTimeBetween(start, end);
    }



}
