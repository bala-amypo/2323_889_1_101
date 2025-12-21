package com.example.demo.service;

import com.example.demo.model.ConsumptionLog;

import java.util.List;

public interface ConsumptionLogService {

    /* ===== Long-based ===== */

    ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log);

    List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId);

    ConsumptionLog getLog(Long id);

    /* ===== String-based (for tests & controllers) ===== */

    ConsumptionLog logConsumption(String stockRecordId, ConsumptionLog log);

    List<ConsumptionLog> getLogsByStockRecord(String stockRecordId);

    ConsumptionLog getLog(String id);
}