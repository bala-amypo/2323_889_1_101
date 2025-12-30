package com.example.demo.controller;
import com.example.demo.model.PredictionRule;
import com.example.demo.service.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/predict")
@RequiredArgsConstructor
public class PredictionController {
    private final PredictionService service;

    @GetMapping("/restock-date/{sid}")
    public String predict(@PathVariable Long sid) {
        LocalDate date = service.predictRestockDate(sid);
        return date.toString(); // Returning string to match test expectation
    }
    @PostMapping("/rules")
    public PredictionRule createRule(@RequestBody PredictionRule r) { return service.createRule(r); }
    @GetMapping("/rules")
    public List<PredictionRule> getAllRules() { return service.getAllRules(); }
}