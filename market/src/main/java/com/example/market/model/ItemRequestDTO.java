package com.example.market.model;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class ItemRequestDTO {
    private String name;
    private Double price;
    private String description;
    private String photoUrl;
}
