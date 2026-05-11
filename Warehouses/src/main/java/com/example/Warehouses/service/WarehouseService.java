package com.example.Warehouses.service;

import com.example.Warehouses.model.Warehouse;
import com.example.Warehouses.model.WarehouseDTO;
import com.example.Warehouses.repository.ProductRepository;
import com.example.Warehouses.repository.ShopRepository;
import com.example.Warehouses.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository, ShopRepository shopRepository, ProductRepository productRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    public Warehouse addWarehouse(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = new Warehouse(
                warehouseDTO.getName()
        );
        warehouseRepository.save(warehouse);
        return warehouse;
    }

    public Warehouse updateWarehouse(int id, WarehouseDTO warehouseDTO) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow();
        warehouse.setName(warehouseDTO.getName());
        warehouseRepository.save(warehouse);
        return warehouse;
    }

    public Warehouse deleteWarehouse(int id) {
       Warehouse warehouse = warehouseRepository.findById(id).orElseThrow();
       warehouseRepository.deleteById(id);
       return warehouse;
    }
}
