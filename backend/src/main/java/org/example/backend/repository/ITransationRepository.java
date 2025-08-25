package org.example.backend.repository;

import org.example.backend.model.Product;
import org.example.backend.model.ProductType;
import org.example.backend.model.Transaction;
import org.example.backend.model.TransactionType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ITransationRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findAllByType(TransactionType transactionType);
    List<Transaction> findAllByUserId(String userId);
    List<Transaction> findAllByProductId(String productId);
    List<Transaction> findAllByTime(LocalDateTime start, LocalDateTime end);
}
