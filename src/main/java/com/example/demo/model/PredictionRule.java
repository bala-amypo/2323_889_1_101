package com.example.demo.model;
import jakarta.persistence.Id;
import jakarta.persistence.Coloumn;

import jakarta.validation.constraints.Min;

public class PredictionRule{
    private Long id;
    @Coloumn(unique=true)
    private String ruleName;
    @Min(1)
    private Integer averageDaysWindow;
    private Integer minDailyUsage;
    private Integer maxDailyUsage;
    private LocalDateTime createdAt;
    
}