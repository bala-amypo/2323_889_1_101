package com.example.demo.model;
import jakarta.persistence.Id;
import jakarta.

public class StockRecord{
    private Long id;
    private product;
    private  warehouse;
    @Min(0)
    private Integer currentQuantity;
    private  Integer reorderThreshold;
    private LocalDateTime lastUpdated;
    
}