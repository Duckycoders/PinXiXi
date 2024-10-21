package com.example.mini_amazon.model;


import lombok.Data;

@Data
public class ProductReview{
    private Long id;
    private Long productId;
    private Long userId;
    private Integer rating;
    private String comment;

    // Getters and Setters
    // ...
}