package com.example.Warehouses.service;

import com.example.Warehouses.model.Product;
import com.example.Warehouses.model.ProductDTO;
import com.example.Warehouses.model.Shop;
import com.example.Warehouses.model.Warehouse;
import com.example.Warehouses.repository.ProductRepository;
import com.example.Warehouses.repository.ShopRepository;
import com.example.Warehouses.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product addProduct(ProductDTO productDTO) {
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
        return product;
    }

    public Product updateProduct(int id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setWarehouse(warehouseRepository.findById(productDTO.getWarehouseId()).orElseThrow());
        product.setShop(shopRepository.findById(productDTO.getShopId()).orElseThrow());
        productRepository.save(product);
        return product;
    }

    public Product deleteProduct(int id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.deleteById(id);
        return product;
    }
}
