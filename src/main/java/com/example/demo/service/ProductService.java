package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product getProduct(Long id);

    Product getProduct(String id);

    List<Product> getAllProducts();
}