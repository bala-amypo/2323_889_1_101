package com.example.demo.service.impl;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository repo;

    @Override
    public Warehouse createWarehouse(Warehouse w) {
        if (repo.findByWarehouseName(w.getWarehouseName()).isPresent()) 
            throw new IllegalArgumentException("Duplicate name");
        return repo.save(w);
    }
    @Override
    public Warehouse getWarehouse(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
    }
    @Override
    public List<Warehouse> getAllWarehouses() { return repo.findAll(); }
}