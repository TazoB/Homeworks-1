package com.example.Warehouses.service;

import com.example.Warehouses.model.Warehouse;
import com.example.Warehouses.model.WarehouseDTO;
import com.example.Warehouses.repository.ProductRepository;
import com.example.Warehouses.repository.ShopRepository;
import com.example.Warehouses.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository, ShopRepository shopRepository, ProductRepository productRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse addWarehouse(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = new Warehouse(
                warehouseDTO.getName()
        );
        warehouseRepository.save(warehouse);
        return warehouse;
    }
}
