package com.example.demo.model;

public class Warehouse{
    private Long id;
    @coloumn(unique=true)
    private String warehouseName;
    @NotBlank="should not be empty"
    private String location;
    private LocalDateTime createdAt; 
    
}