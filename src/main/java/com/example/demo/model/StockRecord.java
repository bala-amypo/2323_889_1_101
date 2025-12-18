package com.example.demo.model;

public class StockRecord{
    private Long id;
    @coloumn(unique=true)
    private String warehouseName;
    @NotBlank="should not be empty"
    private Integer
    private  Integer reorderThreshold;
    private LocalDateTime lastUpdated;
    
}