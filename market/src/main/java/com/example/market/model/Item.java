package com.example.market.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column
    private String description;

    @Column(name = "submission_time")
    private LocalDateTime submissionTime;

    @Column(name = "photo_url")
    private String photoUrl;

    public Item(String name, Double price, String description, LocalDateTime submissionTime, String photoUrl) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.submissionTime = submissionTime;
        this.photoUrl = photoUrl;
    }
}
