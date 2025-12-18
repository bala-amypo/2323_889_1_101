package com.example.demo.model;
import jakarta.persistence.Id;
import jakarta.persistence.Coloumn;
public class Product{
    
    private long id;
    @NotBlank(message="should not be empty")
    private  String productName;
    @coloumn(unique=true)
    private String sku;
    private String category;
    private LocalDateTime createdAt;
}