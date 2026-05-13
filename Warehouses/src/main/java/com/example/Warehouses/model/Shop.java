package com.example.Warehouses.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Product> products;

    @ManyToMany
    @JoinTable(name = "shop_warehouse", joinColumns = @JoinColumn(name = "shop_id"), inverseJoinColumns = @JoinColumn(name = "warehouse_id"))
    private List<Warehouse> warehouses;

    public Shop(String name, List<Product> products, List<Warehouse> warehouses) {
        this.name = name;
        this.products = products;
        this.warehouses = warehouses;
    }
}
