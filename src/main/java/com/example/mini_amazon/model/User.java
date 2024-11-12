package com.example.mini_amazon.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String email;
    private String name;
    private String address;
    private String password;
    private Double balance;
    private Long sellerId;
}