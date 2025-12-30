package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid; // Import this
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    // FIX: Add @Valid here to trigger the 400 Bad Request on invalid input
    public Product create(@RequestBody @Valid Product p) { 
        return service.createProduct(p); 
    }

    @GetMapping
    public List<Product> getAll() { return service.getAllProducts(); }
    
    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) { return service.getProduct(id); }
}