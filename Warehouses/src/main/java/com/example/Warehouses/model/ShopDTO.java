package com.example.Warehouses.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class ShopDTO {
    private String name;
    private List<Integer> productIds;
    private List<Integer> warehouseIds;
}
