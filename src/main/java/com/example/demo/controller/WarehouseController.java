package com.example.demo.controller;
import com.example.demo.model.Warehouse;
import com.example.demo.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService service;

    @PostMapping
    public Warehouse create(@RequestBody Warehouse w) { return service.createWarehouse(w); }
    @GetMapping
    public List<Warehouse> getAll() { return service.getAllWarehouses(); }
    @GetMapping("/{id}")
    public Warehouse get(@PathVariable Long id) { return service.getWarehouse(id); }
}