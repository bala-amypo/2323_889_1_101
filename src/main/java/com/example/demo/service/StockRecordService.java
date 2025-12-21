package com.example.demo.service;

import com.example.demo.model.StockRecord;

import java.util.List;

public interface StockRecordService {

    StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord record);

    StockRecord createStockRecord(String productId, String warehouseId, StockRecord record);

    StockRecord getStockRecord(Long id);

    StockRecord getStockRecord(String id);

    List<StockRecord> getRecordsBy_product(Long productId);

    List<StockRecord> getRecordsBy_product(String productId);

    List<StockRecord> getRecordsByWarehouse(Long warehouseId);

    List<StockRecord> getRecordsByWarehouse(String warehouseId);
}