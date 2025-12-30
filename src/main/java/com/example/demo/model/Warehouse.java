package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String warehouseName;
    private String location;
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() { createdAt = LocalDateTime.now(); }
}