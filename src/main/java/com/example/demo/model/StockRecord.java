package com.example.demo.model;
import jakarta.persistence.Id;
import jakarta.persistence.Coloumn;
import jakarta.validation.constraints.Min;


public class StockRecord{
    private Long id;
    private product;
    @Coloumn(unique=true)
    private  warehouse;
    @Min(0)
    private Integer currentQuantity;
    @Min(1)
    private  Integer reorderThreshold;
    private LocalDateTime lastUpdated;
    
}