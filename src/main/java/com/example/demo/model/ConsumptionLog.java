package com.example.demo.model;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;

public class ConsumptionLog{
    private Long id;
    private stockRecord;
    @Min(1)          
    private Integer consumedQuantity;
    private Integer reorderThreshold;
    private LocalDateTime lastUpdated;
    
     
    
}