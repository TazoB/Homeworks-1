package com.example.Warehouses.controller;

import com.example.Warehouses.model.ProductDTO;
import com.example.Warehouses.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDTO product) {
        productService.addProduct(product);
    }
}
