package com.example.market.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemResponseDTO {
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private String photoUrl;
    private LocalDateTime submissionTime;
}
