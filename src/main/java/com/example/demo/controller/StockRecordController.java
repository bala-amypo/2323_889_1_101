package com.example.demo.controller;
import com.example.demo.model.StockRecord;
import com.example.demo.service.StockRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockRecordController {
    private final StockRecordService service;

    @PostMapping("/{pid}/{wid}")
    public StockRecord create(@PathVariable Long pid, @PathVariable Long wid, @RequestBody StockRecord r) {
        return service.createStockRecord(pid, wid, r);
    }
    @GetMapping("/product/{pid}")
    public List<StockRecord> getByProduct(@PathVariable Long pid) { return service.getRecordsBy_product(pid); }
    @GetMapping("/warehouse/{wid}")
    public List<StockRecord> getByWarehouse(@PathVariable Long wid) { return service.getRecordsByWarehouse(wid); }
    @GetMapping("/{id}")
    public StockRecord get(@PathVariable Long id) { return service.getStockRecord(id); }
}