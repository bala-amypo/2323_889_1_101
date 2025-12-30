package com.example.demo.controller;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/consumption")
@RequiredArgsConstructor
public class ConsumptionLogController {
    private final ConsumptionLogService service;

    @PostMapping("/{sid}")
    public ConsumptionLog log(@PathVariable Long sid, @RequestBody ConsumptionLog log) {
        return service.logConsumption(sid, log);
    }
    @GetMapping("/record/{sid}")
    public List<ConsumptionLog> getByRecord(@PathVariable Long sid) { return service.getLogsByStockRecord(sid); }
    @GetMapping("/{id}")
    public ConsumptionLog get(@PathVariable Long id) { return service.getLog(id); }
}