package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.*;
import org.example.backend.repository.IProductRepository;
import org.example.backend.repository.ITransactionRepository;
import org.example.backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArrivalService implements ITransactionService {
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void execute(Transaction transaction) {
        // Service for ARRIVAL transactions
        // Executed ONLY by ADMIN or OWNER users

        User user = userRepository.findById(transaction.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found."));
        if(!user.getRole().equals(Role.ADMIN) && !user.getRole().equals(Role.OWNER)) {
            throw new IllegalArgumentException("Only ADMIN and OWNER users can execute ARRIVALS.");
        }

        if(!transaction.getTransactionType().equals(TransactionType.ARRIVAL)) {
            throw new IllegalArgumentException("Transaction type must be ARRIVAL.");
        }

        if(transaction.getQuantity() <= 0) {
            throw new IllegalArgumentException("Transaction quantity must be greater than zero.");
        }

        Product product = productRepository.findById(transaction.getProductId()).orElseThrow(() -> new IllegalArgumentException("Product not found."));
        product.setQuantity(product.getQuantity() + transaction.getQuantity());

        productRepository.save(product);
        transactionRepository.save(transaction);
    }
}
