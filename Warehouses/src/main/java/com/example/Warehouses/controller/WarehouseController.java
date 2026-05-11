package com.example.Warehouses.controller;

import com.example.Warehouses.model.Warehouse;
import com.example.Warehouses.model.WarehouseDTO;
import com.example.Warehouses.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> findAll() {
        return ResponseEntity.ok()
                .body(warehouseService.findAll());
    }

    @PostMapping
    public ResponseEntity<Warehouse> addWarehouse(@RequestBody WarehouseDTO warehouse) {
         return ResponseEntity
                 .ok()
                 .body(warehouseService.addWarehouse(warehouse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable int id, @RequestBody WarehouseDTO warehouseDTO) {
        return ResponseEntity.ok()
                .body(warehouseService.updateWarehouse(id, warehouseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Warehouse> deleteWarehouse(@PathVariable int id) {
        return ResponseEntity.ok()
                .body(warehouseService.deleteWarehouse(id));
    }
}
