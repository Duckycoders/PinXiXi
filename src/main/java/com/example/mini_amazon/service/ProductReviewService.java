package com.example.mini_amazon.service;


import com.example.mini_amazon.mapper.ProductReviewMapper;
import com.example.mini_amazon.model.ProductReview;
import com.example.mini_amazon.model.UserReviewHelpfulness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductReviewService {

    @Autowired
    private ProductReviewMapper productReviewMapper;

    public void addReview(ProductReview review) {
        productReviewMapper.addReview(review);
    }

    public List<ProductReview> getReviewsByUserId(Long userId) {
        return productReviewMapper.findByUserId(userId);
    }

    public ProductReview getReviewById(Long id) {
        return productReviewMapper.findById(id);
    }

    public void updateReview(ProductReview review, Long userId) {
        review.setUserId(userId);
        productReviewMapper.updateReview(review);
    }

    @Transactional
    public void submitReview(ProductReview review, Long userId) {
        review.setUserId(userId);
        ProductReview existingReview = productReviewMapper.getUserReviewForProduct(review.getProductId(), userId);
        if (existingReview == null) {
            productReviewMapper.addReview(review);
        } else {
            review.setId(existingReview.getId());
            productReviewMapper.updateReview(review);
        }
    }


    public void deleteReview(Long id, Long userId) {
        productReviewMapper.deleteReview(id, userId);
    }

    public List<ProductReview> getProductReviews(Long productId, Long userId) {
        return productReviewMapper.findByProductId(productId, userId);
    }

    public List<ProductReview> getProductReviewsByUserId(Long userId) {
        return productReviewMapper.getProductReviewsByUserId(userId);
    }

    @Transactional
    public void markReviewHelpful(Integer reviewId, Long userId) {
        // 先查询用户是否已经标记过该评论为有帮助
        UserReviewHelpfulness existingMark = productReviewMapper.getUserReviewHelpfulness(reviewId, userId);
        if (existingMark != null && existingMark.getIsHelpful()) {
            // 如果已经标记过有帮助，直接返回
            return;
        }

        // 更新评论的点赞数
        productReviewMapper.updateHelpfulnessCount(reviewId, 1);
        UserReviewHelpfulness newMark = new UserReviewHelpfulness();
        newMark.setUserId(userId);
        newMark.setReviewId(reviewId);
        // 如果之前没有标记过，插入新的记录
        if (existingMark == null) {
            newMark.setIsHelpful(true);
            productReviewMapper.insertUserReviewHelpfulness(newMark);
        } else if (!existingMark.getIsHelpful()) {
            // 无帮助 -> 有帮助
            newMark.setIsHelpful(true);
            productReviewMapper.updateUserReviewHelpfulness(newMark);
        }
    }

    @Transactional
    public void markReviewNotHelpful(Integer reviewId, Long userId) {
        // 先查询用户是否已经标记过该评论为无帮助
        UserReviewHelpfulness existingMark = productReviewMapper.getUserReviewHelpfulness(reviewId, userId);
        if (existingMark != null && !existingMark.getIsHelpful()) {
            // 如果已经标记过无帮助，直接返回
            return;
        }
        // 更新评论的点赞数
        productReviewMapper.updateHelpfulnessCount(reviewId, -1);
        UserReviewHelpfulness newMark = new UserReviewHelpfulness();
        newMark.setUserId(userId);
        newMark.setReviewId(reviewId);
        // 如果之前没有标记过，插入新的记录
        if (existingMark == null) {
            newMark.setIsHelpful(false);
            productReviewMapper.insertUserReviewHelpfulness(newMark);
        } else if (existingMark.getIsHelpful()) {
            // 有帮助 -> 无帮助
            newMark.setIsHelpful(false);
            productReviewMapper.updateUserReviewHelpfulness(newMark);
        }
    }
}