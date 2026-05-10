package com.example.Warehouses.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Product(String name, Double price, Integer quantity, Shop shop, Warehouse warehouse) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.shop = shop;
        this.warehouse = warehouse;
    }
}
