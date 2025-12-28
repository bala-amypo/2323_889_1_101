// package com.example.demo.controller;

// import com.example.demo.model.Warehouse;
// import com.example.demo.service.WarehouseService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/warehouses")
// public class WarehouseController {

//     private final WarehouseService warehouseService;

//     public WarehouseController(WarehouseService warehouseService) {
//         this.warehouseService = warehouseService;
//     }

//     @PostMapping
//     public Warehouse createWarehouse(@RequestBody Warehouse warehouse) {
//         return warehouseService.createWarehouse(warehouse);
//     }

//     @GetMapping
//     public List<Warehouse> getAllWarehouses() {
//         return warehouseService.getAllWarehouses();
//     }

//     @GetMapping("/{id}")
//     public Warehouse getWarehouse(@PathVariable Long id) {
//         return warehouseService.getWarehouse(id);
//     }
// }package com.example.demo.controller;

import com.example.demo.model.Warehouse;
import com.example.demo.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        return ResponseEntity.ok(warehouseService.createWarehouse(warehouse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouse(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.getWarehouse(id));
    }
}