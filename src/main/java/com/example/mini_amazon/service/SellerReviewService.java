package com.example.mini_amazon.service;


import com.example.mini_amazon.mapper.SellerReviewMapper;
import com.example.mini_amazon.model.SellerReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerReviewService {
    @Autowired
    private SellerReviewMapper sellerReviewMapper;

    public void addReview(SellerReview review) {
        sellerReviewMapper.addReview(review);
    }

    public List<SellerReview> getReviewsBySellerId(Integer sellerId) {
        return sellerReviewMapper.findBySellerId(sellerId);
    }

    public List<SellerReview> getReviewsByUserId(Integer userId) {
        return sellerReviewMapper.findByUserId(userId);
    }

    public SellerReview getReviewById(Integer id) {
        return sellerReviewMapper.findById(id);
    }

    public void updateReview(SellerReview review) {
        sellerReviewMapper.updateReview(review);
    }

    public void deleteReview(Integer id) {
        sellerReviewMapper.deleteReview(id);
    }
}