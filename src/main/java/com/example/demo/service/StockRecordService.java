package com.example.demo.service;
import com.example.demo.model.StockRecord;
import java.util.List;

public interface StockRecordService {
    StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord record);
    StockRecord getStockRecord(Long id);
    // Method name must match exactly what Test t14 expects
    List<StockRecord> getRecordsBy_product(Long productId); 
    List<StockRecord> getRecordsByWarehouse(Long warehouseId);
}