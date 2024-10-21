package com.example.mini_amazon.model;

import lombok.Data;

@Data
public class InventoryDTO {
    private Long id;
    private Long productId;
    private String productName;
    private int stockQuantity;

    // Getters and setters
}