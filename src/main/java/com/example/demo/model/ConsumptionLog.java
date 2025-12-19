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
     public StudentEntity(long id, @NotBlank(message = "should not contain spaces") String name,   @NotBlank(message = "no blank allowed") String email) {
      this.id = id;
      this.name = name;
      this.email = email;
    }
    public long getId() {
       return id;
    }
    public void setId(long id) {
       this.id = id;
    }
    public String getName() {
       return name;
    }
    public void setName(String name) {
       this.name = name;
    }
    public String getEmail() {
       return email;
    }
    public void setEmail(String email) {
       this.email = email;
    }
     
    
}