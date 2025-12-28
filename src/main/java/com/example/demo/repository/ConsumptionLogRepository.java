// package com.example.demo.repository;

// import com.example.demo.model.ConsumptionLog;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.time.LocalDate;
// import java.util.List;

// public interface ConsumptionLogRepository
//         extends JpaRepository<ConsumptionLog, Long> {

//     // Used by services + tests
//     List<ConsumptionLog> findByStockRecordId(Long stockRecordId);

//     // Used by tests
//     List<ConsumptionLog>
//     findByStockRecordIdAndConsumedDateBetween(
//             long stockRecordId,
//             LocalDate start,
//             LocalDate end
//     );

//     // Used by tests
//     List<ConsumptionLog>
//     findByStockRecordIdOrderByConsumedDateDesc(long stockRecordId);
// }