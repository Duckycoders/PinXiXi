package com.example.mini_amazon.model;



import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private Integer id;
    private Integer userId;
    private Integer sellerId;
    private String message;
    private Date createdAt;

    // Getters and Setters
    // ...
}

