package com.example.Warehouses.model;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {
    private String name;
    private double price;
    private int quantity;
    private int warehouseId;
    private int shopId;
}
