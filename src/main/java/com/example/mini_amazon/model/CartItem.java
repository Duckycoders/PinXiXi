package com.example.mini_amazon.model;

import lombok.Data;

@Data
public class CartItem{
    private Long id;
    private Long cartId;
    private Long productId;
    private Long sellerId;
    private Integer quantity;

    // Getters and Setters
    // ...
}