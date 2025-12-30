package com.example.demo.repository;

import com.example.demo.model.ConsumptionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ConsumptionLogRepository extends JpaRepository<ConsumptionLog, Long> {
    List<ConsumptionLog> findByStockRecordId(Long stockRecordId);
    List<ConsumptionLog> findByStockRecordIdAndConsumedDateBetween(Long stockRecordId, LocalDate start, LocalDate end);
    List<ConsumptionLog> findByStockRecordIdOrderByConsumedDateDesc(Long stockRecordId);
}
