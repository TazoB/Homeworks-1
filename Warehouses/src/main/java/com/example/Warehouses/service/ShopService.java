package com.example.Warehouses.service;

import com.example.Warehouses.model.Product;
import com.example.Warehouses.model.Shop;
import com.example.Warehouses.model.ShopDTO;
import com.example.Warehouses.model.Warehouse;
import com.example.Warehouses.repository.ProductRepository;
import com.example.Warehouses.repository.ShopRepository;
import com.example.Warehouses.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {
    private final ShopRepository shopRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;

    public ShopService(ShopRepository shopRepository, WarehouseRepository warehouseRepository, ProductRepository productRepository) {
        this.shopRepository = shopRepository;
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
    }

    public void addShop(ShopDTO shopDTO) {
        List<Product> products = getProducts(shopDTO.getProductIds());
        List<Warehouse> warehouses = getWarehouses(shopDTO.getWarehouseIds());

        Shop shop = new Shop(
                shopDTO.getName(),
                products,
                warehouses
        );
        shopRepository.save(shop);
    }

    private List<Product> getProducts(List<Integer> ids) {
        List<Product> products = new ArrayList<>();
        for (Integer id : ids) {
            products.add(productRepository.findById(id).orElseThrow());
        }
        return products;
    }

    private List<Warehouse> getWarehouses(List<Integer> ids) {
        List<Warehouse> warehouses = new ArrayList<>();
        for (Integer id : ids) {
            warehouses.add(warehouseRepository.findById(id).orElseThrow());
        }
        return warehouses;
    }
}
