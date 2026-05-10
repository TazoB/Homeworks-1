package com.example.Warehouses.service;

import com.example.Warehouses.model.Product;
import com.example.Warehouses.model.ProductDTO;
import com.example.Warehouses.model.Shop;
import com.example.Warehouses.model.Warehouse;
import com.example.Warehouses.repository.ProductRepository;
import com.example.Warehouses.repository.ShopRepository;
import com.example.Warehouses.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final ShopRepository shopRepository;

    public ProductService(ProductRepository repository, WarehouseRepository warehouseRepository, ShopRepository shopRepository) {
        this.productRepository = repository;
        this.warehouseRepository = warehouseRepository;
        this.shopRepository = shopRepository;
    }

    public void addProduct(ProductDTO productDTO) {
        Warehouse warehouse = warehouseRepository.findById(productDTO.getWarehouseId()).orElseThrow();
        Shop shop = shopRepository.findById(productDTO.getShopId()).orElseThrow();

        Product product = new Product(
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getQuantity(),
                shop,
                warehouse
        );
        productRepository.save(product);
    }
}
