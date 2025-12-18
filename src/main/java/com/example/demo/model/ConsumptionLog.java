package com.example.demo.model;

public class ConsumptionLog{
    private Long id;
    private stockRecord;
    @Min(1)
    private Integer  Integer consumedQuantity;
    private Integer reorderThreshold;
    private LocalDateTime lastUpdated;
    
}