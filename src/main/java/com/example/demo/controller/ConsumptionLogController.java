// package com.example.demo.controller;

// import com.example.demo.model.ConsumptionLog;
// import com.example.demo.service.ConsumptionLogService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/consumption")
// public class ConsumptionLogController {

//     private final ConsumptionLogService consumptionLogService;

//     public ConsumptionLogController(ConsumptionLogService consumptionLogService) {
//         this.consumptionLogService = consumptionLogService;
//     }

//     @PostMapping("/{stockRecordId}")
//     public ConsumptionLog logConsumption(
//             @PathVariable Long stockRecordId,
//             @RequestBody ConsumptionLog log) {

//         return consumptionLogService.logConsumption(stockRecordId, log);
//     }

//     @GetMapping("/record/{stockRecordId}")
//     public List<ConsumptionLog> getLogsByStockRecord(
//             @PathVariable Long stockRecordId) {

//         return consumptionLogService.getLogsByStockRecord(stockRecordId);
//     }

//     @GetMapping("/{id}")
//     public ConsumptionLog getLog(@PathVariable Long id) {
//         return consumptionLogService.getLog(id);
//     }
// }
package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/consumption")
public class ConsumptionLogController {
    @Autowired
    private ConsumptionLogService service;

    @PostMapping("/{stockRecordId}")
    public ResponseEntity<ConsumptionLog> logConsumption(@PathVariable Long stockRecordId,
                                                         @RequestBody ConsumptionLog log) {
        return ResponseEntity.ok(service.logConsumption(stockRecordId, log));
    }

    @GetMapping("/record/{stockRecordId}")
    public ResponseEntity<List<ConsumptionLog>> getLogs(@PathVariable Long stockRecordId) {
        return ResponseEntity.ok(service.getLogsByStockRecord(stockRecordId));
    }
}