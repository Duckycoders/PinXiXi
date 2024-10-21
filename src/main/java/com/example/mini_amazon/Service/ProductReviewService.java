package com.example.mini_amazon.Service;


import com.example.mini_amazon.mapper.ProductReviewMapper;
import com.example.mini_amazon.model.ProductReview;
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

    public List<ProductReview> getReviewsByProductId(Long productId) {
        return productReviewMapper.findByProductId(productId);
    }

    public List<ProductReview> getReviewsByUserId(Long userId) {
        return productReviewMapper.findByUserId(userId);
    }

    public ProductReview getReviewById(Long id) {
        return productReviewMapper.findById(id);
    }

    public void updateReview(ProductReview review) {
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


    public void deleteReview(Long id) {
        productReviewMapper.deleteReview(id);
    }

    public List<ProductReview> getProductReviews(Long productId) {
        return productReviewMapper.findByProductId(productId);
    }
}