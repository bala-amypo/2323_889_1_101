package com.example.demo.repository;

import com.example.demo.model.StockRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StockRecordRepository extends JpaRepository<StockRecord, Long> {
    // Used by getRecordsBy_product
    List<StockRecord> findByProductId(Long productId);
    
    // Used by getRecordsByWarehouse
    List<StockRecord> findByWarehouseId(Long warehouseId);
    
    // FIX: This was missing, causing the error in createStockRecord
    Optional<StockRecord> findByProductIdAndWarehouseId(Long productId, Long warehouseId);
}