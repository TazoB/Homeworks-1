package com.example.Warehouses.controller;

import com.example.Warehouses.model.Warehouse;
import com.example.Warehouses.model.WarehouseDTO;
import com.example.Warehouses.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public ResponseEntity<Warehouse> addWarehouse(@RequestBody WarehouseDTO warehouse) {
         return ResponseEntity
                 .ok()
                 .body(warehouseService.addWarehouse(warehouse));
    }
}
