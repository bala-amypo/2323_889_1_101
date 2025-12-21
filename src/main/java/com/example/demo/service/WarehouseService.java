package com.example.demo.service;

import com.example.demo.model.Warehouse;

import java.util.List;

public interface WarehouseService {

    Warehouse createWarehouse(Warehouse warehouse);

    Warehouse getWarehouse(Long id);

    Warehouse getWarehouse(String id);

    List<Warehouse> getAllWarehouses();
}