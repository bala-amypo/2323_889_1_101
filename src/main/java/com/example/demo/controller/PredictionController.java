// package com.example.demo.controller;

// import com.example.demo.dto.PredictionResponse;
// import com.example.demo.model.PredictionRule;
// import com.example.demo.service.PredictionService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/predict")
// public class PredictionController {

//     private final PredictionService predictionService;

//     public PredictionController(PredictionService predictionService) {
//         this.predictionService = predictionService;
//     }

//     @GetMapping("/restock-date/{stockRecordId}")
//     public PredictionResponse predictRestockDate(
//             @PathVariable Long stockRecordId) {

//         return new PredictionResponse(
//                 predictionService.predictRestockDate(stockRecordId));
//     }

//     @PostMapping("/rules")
//     public PredictionRule createRule(@RequestBody PredictionRule rule) {
//         return predictionService.createRule(rule);
//     }

//     @GetMapping("/rules")
//     public List<PredictionRule> getAllRules() {
//         return predictionService.getAllRules();
//     }
// }
package com.example.demo.controller;

import com.example.demo.model.PredictionRule;
import com.example.demo.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/predict")
public class PredictionController {
    @Autowired
    private PredictionService predictionService;

    @PostMapping("/rules")
    public ResponseEntity<PredictionRule> createRule(@RequestBody PredictionRule rule) {
        return ResponseEntity.ok(predictionService.createRule(rule));
    }

    @GetMapping("/rules")
    public ResponseEntity<List<PredictionRule>> getRules() {
        return ResponseEntity.ok(predictionService.getAllRules());
    }

    @GetMapping("/restock-date/{stockRecordId}")
    public ResponseEntity<String> predictDate(@PathVariable Long stockRecordId) {
        LocalDate date = predictionService.predictRestockDate(stockRecordId);
        return ResponseEntity.ok(date.toString());
    }
}