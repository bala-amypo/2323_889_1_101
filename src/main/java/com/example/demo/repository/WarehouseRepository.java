package com.example.demo.repository;

import com.example.demo.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    // FIX: This was missing, causing the error in createWarehouse
    Optional<Warehouse> findByWarehouseName(String warehouseName);
}