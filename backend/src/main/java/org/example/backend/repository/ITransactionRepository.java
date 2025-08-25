package org.example.backend.repository;

import org.example.backend.model.Transaction;
import org.example.backend.model.TransactionType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ITransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByTransactionType(TransactionType transactionType);
    List<Transaction> findByUserId(String userId);
    List<Transaction> findByProductId(String productId);
    List<Transaction> findByTimeBetween(LocalDateTime start, LocalDateTime end);
}
