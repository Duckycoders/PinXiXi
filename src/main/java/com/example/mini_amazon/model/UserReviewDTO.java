package com.example.mini_amazon.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserReviewDTO {
    private Integer id;
    private Integer rating;
    private String comment;
    private String productName;
    private Date reviewTime;
    private Integer helpfulnessCount;
    private String helpfulType;
}