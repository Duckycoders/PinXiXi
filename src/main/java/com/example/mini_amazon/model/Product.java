package com.example.mini_amazon.model;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    private Integer id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private Integer quantity;
    private Integer categoryId;
    private Long sellerId;
    private List<ProductInventory> inventories;
    private List<ProductReview> reviews;

    // Getters and Setters
    // ...
}