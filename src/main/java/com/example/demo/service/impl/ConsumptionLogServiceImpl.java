package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.ConsumptionLogService;
import com.example.demo.util.DateUtil; // Import DateUtil
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumptionLogServiceImpl implements ConsumptionLogService {
    private final ConsumptionLogRepository repo;
    private final StockRecordRepository stockRepo;

    @Override
    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {
        StockRecord sr = stockRepo.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found")); 
        
        // Use DateUtil as per requirements
        if (DateUtil.isFuture(log.getConsumedDate())) {
            throw new IllegalArgumentException("consumedDate cannot be future"); 
        }
        
        log.setStockRecord(sr);
        
        // Update stock quantity logic (ensure it doesn't drop below 0)
        int newQty = Math.max(0, sr.getCurrentQuantity() - log.getConsumedQuantity());
        sr.setCurrentQuantity(newQty);
        stockRepo.save(sr);
        
        return repo.save(log);
    }

    @Override
    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return repo.findByStockRecordId(stockRecordId);
    }

    @Override
    public ConsumptionLog getLog(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConsumptionLog not found"));
    }
}