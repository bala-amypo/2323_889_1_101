package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConsumptionLogServiceImpl implements ConsumptionLogService {

    private final ConsumptionLogRepository logRepository;
    private final StockRecordRepository stockRecordRepository;

    public ConsumptionLogServiceImpl(
            ConsumptionLogRepository logRepository,
            StockRecordRepository stockRecordRepository) {
        this.logRepository = logRepository;
        this.stockRecordRepository = stockRecordRepository;
    }

    /* ===== Long-based ===== */

    @Override
    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {

        StockRecord record = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        if (log.getConsumedQuantity() <= 0) {
            throw new IllegalArgumentException("consumedQuantity must be > 0");
        }

        if (log.getConsumedDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("consumedDate cannot be future");
        }

        log.setStockRecord(record);
        return logRepository.save(log);
    }

    @Override
    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return logRepository.findByStockRecordId(stockRecordId);
    }

    @Override
    public ConsumptionLog getLog(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConsumptionLog not found"));
    }

    /* ===== String-based ===== */

    @Override
    public ConsumptionLog logConsumption(String stockRecordId, ConsumptionLog log) {
        return logConsumption(Long.parseLong(stockRecordId), log);
    }

    @Override
    public List<ConsumptionLog> getLogsByStockRecord(String stockRecordId) {
        return getLogsByStockRecord(Long.parseLong(stockRecordId));
    }

    @Override
    public ConsumptionLog getLog(String id) {
        return getLog(Long.parseLong(id));
    }
}