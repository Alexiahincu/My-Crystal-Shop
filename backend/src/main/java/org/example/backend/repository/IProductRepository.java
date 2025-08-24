package org.example.backend.repository;

import org.example.backend.model.Product;
import org.example.backend.model.ProductType;
import org.example.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends MongoRepository<Product, String> {
    List<Product> findAllByType(ProductType productType);
    List<Product> findByName(String nameFragment);
}
