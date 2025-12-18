package com.example.demo.model;
import jakarta.persistence.Id;
import jakarta.persistence.Coloumn;

public class Warehouse{
    private Long id;
    @Coloumn(unique=true)
    private String warehouseName;
    @NotBlank(message="should not be empty")
    private String location;
    private LocalDateTime createdAt; 
    
}