package com.example.demo.model;

public class PredictionRule{
    private Long id;
    @coloumn(unique=true)
    private String ruleName;
    @Min(1)
    private Integer averageDaysWindow;
    private Integer minDailyUsage;
    private Integer maxDailyUsage;
    private LocalDateTime createdAt;
    
}