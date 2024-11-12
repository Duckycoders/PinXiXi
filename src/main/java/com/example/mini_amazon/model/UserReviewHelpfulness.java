package com.example.mini_amazon.model;

import lombok.Data;

@Data
public class UserReviewHelpfulness {
    private Integer id;
    private Long userId;
    private Integer reviewId;
    private Boolean isHelpful;
}