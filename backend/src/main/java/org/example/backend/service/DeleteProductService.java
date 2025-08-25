package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.Product;
import org.example.backend.model.Transaction;
import org.example.backend.repository.IProductRepository;
import org.example.backend.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteProductService implements ITransactionService{
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ITransactionRepository transactionRepository;

    @Override
    public void execute(Product product, Transaction transaction) {
        //#TODO add validation

        productRepository.delete(product);
        transactionRepository.save(transaction);
    }
}
