package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictionServiceImpl implements PredictionService {
    private final PredictionRuleRepository ruleRepo;
    private final StockRecordRepository stockRepo;
    private final ConsumptionLogRepository logRepo;

    @Override
    public PredictionRule createRule(PredictionRule rule) {
        return ruleRepo.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return ruleRepo.findAll();
    }

    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {
        StockRecord sr = stockRepo.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));
        
        // Simple prediction logic as per requirements
        // Uses default rule (e.g. 7 days average) if no specific rule logic provided
        int daysWindow = 7;
        LocalDate start = LocalDate.now().minusDays(daysWindow);
        List<ConsumptionLog> logs = logRepo.findByStockRecordIdAndConsumedDateBetween(stockRecordId, start, LocalDate.now());
        
        double totalConsumed = logs.stream().mapToInt(ConsumptionLog::getConsumedQuantity).sum();
        double dailyAvg = totalConsumed / daysWindow;
        
        if (dailyAvg <= 0) return LocalDate.now().plusYears(1); // No usage
        
        int quantityNeeded = Math.max(0, sr.getCurrentQuantity() - sr.getReorderThreshold());
        long daysLeft = (long) (quantityNeeded / dailyAvg);
        
        return LocalDate.now().plusDays(daysLeft);
    }
}