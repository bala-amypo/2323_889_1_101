package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public Product createProduct(Product product) {
        if (product.getProductName() == null || product.getProductName().isEmpty()) 
            throw new IllegalArgumentException("Name required");
        if (repository.findBySku(product.getSku()).isPresent()) 
            throw new IllegalArgumentException("Duplicate SKU");
        return repository.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() { return repository.findAll(); }
}