package com.example.Warehouses.controller;

import com.example.Warehouses.model.Product;
import com.example.Warehouses.model.ProductDTO;
import com.example.Warehouses.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok()
                .body(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO product) {
        return ResponseEntity.ok()
                .body(productService.addProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok()
                .body(productService.updateProduct(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        return ResponseEntity.ok()
                .body(productService.deleteProduct(id));
    }
}
