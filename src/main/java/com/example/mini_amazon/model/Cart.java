package com.example.mini_amazon.model;

import lombok.Data;

import java.util.List;


@Data
public class Cart{
    private Long id;
    private Long userId;
    private List<CartItem> items;
}