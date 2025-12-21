package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.StockRecord;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.service.StockRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockRecordServiceImpl implements StockRecordService {

    private final StockRecordRepository stockRecordRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public StockRecordServiceImpl(
            StockRecordRepository stockRecordRepository,
            ProductRepository productRepository,
            WarehouseRepository warehouseRepository) {

        this.stockRecordRepository = stockRecordRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    /* ================== CORE METHODS ================== */

    @Override
    public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord record) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));

        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Warehouse not found"));

        stockRecordRepository.findByProductId(productId).forEach(existing -> {
            if (existing.getWarehouse().getId().equals(warehouseId)) {
                throw new IllegalArgumentException("StockRecord already exists");
            }
        });

        if (record.getCurrentQuantity() < 0) {
            throw new IllegalArgumentException("currentQuantity must be >= 0");
        }

        if (record.getReorderThreshold() <= 0) {
            throw new IllegalArgumentException("reorderThreshold must be > 0");
        }

        record.setProduct(product);
        record.setWarehouse(warehouse);
        record.setLastUpdated(LocalDateTime.now());

        return stockRecordRepository.save(record);
    }

    @Override
    public StockRecord getStockRecord(Long id) {
        return stockRecordRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("StockRecord not found"));
    }

    @Override
    public List<StockRecord> getRecordsBy_product(Long productId) {
        return stockRecordRepository.findByProductId(productId);
    }

    @Override
    public List<StockRecord> getRecordsByWarehouse(Long warehouseId) {
        return stockRecordRepository.findByWarehouseId(warehouseId);
    }

    /* ================== STRING OVERLOADS (FOR TESTS) ================== */

    @Override
    public StockRecord createStockRecord(String productId, String warehouseId, StockRecord record) {
        return createStockRecord(
                Long.parseLong(productId),
                Long.parseLong(warehouseId),
                record
        );
    }

    @Override
    public StockRecord getStockRecord(String id) {
        return getStockRecord(Long.parseLong(id));
    }

    @Override
    public List<StockRecord> getRecordsBy_product(String productId) {
        return getRecordsBy_product(Long.parseLong(productId));
    }

    @Override
    public List<StockRecord> getRecordsByWarehouse(String warehouseId) {
        return getRecordsByWarehouse(Long.parseLong(warehouseId));
    }
}