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
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Product> products;

    @ManyToMany(mappedBy = "warehouses")
    private List<Shop> shops;

    public Warehouse(String name) {
        this.name = name;
    }
}
