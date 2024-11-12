package com.example.mini_amazon.model;


import lombok.Data;

import java.util.Date;
@Data
public class SellerReview{
    private Integer id;
    private Integer sellerId;
    private Integer userId;
    private Integer rating;
    private String comment;
    private Date createdAt;
    private Date updatedAt;

    public void setId(Integer id) {
    }

    // Getters and Setters
    // ...
}
