package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumption")
public class ConsumptionLogController {

    private final ConsumptionLogService consumptionLogService;

    public ConsumptionLogController(ConsumptionLogService consumptionLogService) {
        this.consumptionLogService = consumptionLogService;
    }

    @PostMapping("/{stockRecordId}")
    public ConsumptionLog logConsumption(
            @PathVariable String stockRecordId,
            @RequestBody ConsumptionLog log) {

        return consumptionLogService.logConsumption(stockRecordId, log);
    }

    @GetMapping("/record/{stockRecordId}")
    public List<ConsumptionLog> getLogsByStockRecord(
            @PathVariable String stockRecordId) {

        return consumptionLogService.getLogsByStockRecord(stockRecordId);
    }

    @GetMapping("/{id}")
    public ConsumptionLog getLog(@PathVariable String id) {
        return consumptionLogService.getLog(id);
    }
}