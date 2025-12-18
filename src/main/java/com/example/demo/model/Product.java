package com.example.demo.model;

public class Product{
    
    private long id;
    private  String productName;
    @coloumn(uni)
    private String sku;
    private String category;
    private LocalDateTime createdAt;
}