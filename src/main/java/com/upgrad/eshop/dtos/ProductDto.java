package com.upgrad.eshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String category;
    private double price;
    private String description;
    private String manufacturer;
    private int availableItems;
    private String imageUrl;
    private LocalDateTime created;
    private LocalDateTime updated;

}
