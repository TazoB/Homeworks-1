package com.example.Warehouses.controller;

import com.example.Warehouses.model.ShopDTO;
import com.example.Warehouses.service.ShopService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping
    public void addShop(@RequestBody ShopDTO shop) {
        shopService.addShop(shop);
    }
}
