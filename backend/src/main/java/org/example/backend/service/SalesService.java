package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.Product;
import org.example.backend.model.Transaction;
import org.example.backend.model.TransactionType;
import org.example.backend.repository.IProductRepository;
import org.example.backend.repository.ITransactionRepository;
import org.example.backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SalesService implements ITransactionService {
    @Autowired
    ITransactionRepository transactionRepository;

    @Autowired
    IProductRepository productRepository;

    @Autowired
    IUserRepository userRepository;

    @Override
    public void execute(Transaction transaction) {
        // Service for SALES transactions
        // Executed by any USER role or guests

        if(!transaction.getTransactionType().equals(TransactionType.SALE)) {
            throw new IllegalArgumentException("Transaction type must be SALE.");
        }

        // test comment
        if(transaction.getQuantity() <= 0) {
            throw new IllegalArgumentException("Transaction quantity must be greater than zero.");
        }

        Product product = productRepository.findById(transaction.getProductId()).orElseThrow(() -> new IllegalArgumentException("Product not found."));
        if(product.getQuantity() < transaction.getQuantity()) {
            throw new IllegalArgumentException("Insufficient product quantity for sale.");
        }
        product.setQuantity(product.getQuantity() - transaction.getQuantity());

        productRepository.save(product);
        transactionRepository.save(transaction);
    }
}
