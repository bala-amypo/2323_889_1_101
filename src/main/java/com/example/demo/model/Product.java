package com.example.demo.model;

public class Product{
    
    private long id;
    @NotBlank="should not be empty"
    private  String productName;
    @coloumn(unique=true)
    private String sku;
    private String category;
    private LocalDateTime createdAt;
}