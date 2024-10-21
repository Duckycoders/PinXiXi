package com.example.mini_amazon.model;


import lombok.Data;

@Data
public class ProductInventory {
    private Integer id;
    private Integer productId;
    private Integer sellerId;
    private Integer stockQuantity;

    // Getters and Setters
    // ...
}
