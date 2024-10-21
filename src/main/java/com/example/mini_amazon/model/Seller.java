package com.example.mini_amazon.model;

import lombok.Data;

@Data
public class Seller {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private Long userId;
}