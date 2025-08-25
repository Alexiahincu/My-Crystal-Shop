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
public class WithdrawalService implements ITransactionService{
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void execute(Transaction transaction) {
        // Service for WITHDRAWAL transactions
        // Executed ONLY by ADMIN or OWNER users

        User user = userRepository.findById(transaction.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found."));
        if(!user.getRole().equals(Role.ADMIN) && !user.getRole().equals(Role.OWNER)) {
            throw new IllegalArgumentException("Only ADMIN and OWNER users can execute WITHDRAWALS.");
        }

        if(!transaction.getTransactionType().equals(TransactionType.WITHDRAWAL)) {
            throw new IllegalArgumentException("Transaction type must be WITHDRAWAL.");
        }

        if(transaction.getQuantity() <= 0) {
            throw new IllegalArgumentException("Transaction quantity must be greater than zero.");
        }

        Product product = productRepository.findById(transaction.getProductId()).orElseThrow(() -> new IllegalArgumentException("Product not found."));
        if(product.getQuantity() < transaction.getQuantity()) {
            throw new IllegalArgumentException("Insufficient product quantity for withdrawal.");
        }
        product.setQuantity(product.getQuantity() - transaction.getQuantity());

        productRepository.save(product);
        transactionRepository.save(transaction);
    }
}
