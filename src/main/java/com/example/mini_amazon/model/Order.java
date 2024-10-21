package com.example.mini_amazon.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class Order{
    private Long id;
    private Long userId;
    private BigDecimal totalAmount;
    private int itemCount;
    private String status;

    // Getters and Setters
    // ...
}