package com.example.mini_amazon.model;

import lombok.Data;

@Data
public class AddInventoryRequest {
    private Long productId;
    private Long inventoryId;
    private Long sellerId;
    private int stockQuantity;
}