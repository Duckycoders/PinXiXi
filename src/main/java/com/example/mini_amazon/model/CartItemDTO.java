package com.example.mini_amazon.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDTO {
    private Long id;
    private Long productId;
    private String productName;
    private Long sellerId;
    private int quantity;
    private BigDecimal price;
}