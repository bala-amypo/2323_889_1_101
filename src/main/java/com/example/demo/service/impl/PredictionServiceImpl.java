package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.PredictionRule;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.PredictionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRuleRepository ruleRepository;
    private final StockRecordRepository stockRecordRepository;
    private final ConsumptionLogRepository logRepository;

    public PredictionServiceImpl(
            PredictionRuleRepository ruleRepository,
            StockRecordRepository stockRecordRepository,
            ConsumptionLogRepository logRepository) {

        this.ruleRepository = ruleRepository;
        this.stockRecordRepository = stockRecordRepository;
        this.logRepository = logRepository;
    }

    @Override
    public PredictionRule createRule(PredictionRule rule) {

        if (rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("averageDaysWindow must be > 0");
        }
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("minDailyUsage must be <= maxDailyUsage");
        }

        ruleRepository.findByRuleName(rule.getRuleName())
                .ifPresent(r -> {
                    throw new IllegalArgumentException("ruleName already exists");
                });

        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepository.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return ruleRepository.findAll();
    }
    @Override
public LocalDate predictRestockDate(String stockRecordId) {
    return predictRestockDate(Long.parseLong(stockRecordId));
}


    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {

        StockRecord record = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("StockRecord not found"));

        List<ConsumptionLog> logs =
                logRepository.findByStockRecordId(stockRecordId);

        if (logs.isEmpty()) {
            return LocalDate.now();
        }

        double avgDailyUsage =
                logs.stream().mapToInt(ConsumptionLog::getConsumedQuantity).average().orElse(0);

        int remaining =
                record.getCurrentQuantity() - record.getReorderThreshold();

        int days = avgDailyUsage == 0 ? 0 : (int) Math.ceil(remaining / avgDailyUsage);

        return LocalDate.now().plusDays(Math.max(days, 0));
    }
}