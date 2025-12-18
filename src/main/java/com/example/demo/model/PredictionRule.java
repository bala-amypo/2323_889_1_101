package com.example.demo.model;

public class PredictionRule{
    private Long id;
    private String ruleNama;
    @Min(1)
    private Integer consumedQuantity;
    private LocalDate consumedDate;
    
}