package com.example.mini_amazon.controller;


import com.example.mini_amazon.service.ProductReviewService;
import com.example.mini_amazon.model.ProductReview;
import com.example.mini_amazon.model.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ProductReviewController {
    @Autowired
    private ProductReviewService productReviewService;

    @GetMapping("/product/{productId}")
    public List<ProductReview> getProductReviews(@PathVariable Long productId) {
        return productReviewService.getProductReviews(productId, UserStorage.getCurrentUser().getId());
    }

    @PostMapping("/product/{productId}")
    public ResponseEntity<Void> submitReview(@PathVariable Long productId, @RequestBody ProductReview review) {
        review.setProductId(productId);
        productReviewService.submitReview(review, UserStorage.getCurrentUser().getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/product")
    public ResponseEntity<Void> updateReview(/*@PathVariable Long reviewId,*/ @RequestBody ProductReview review) {
        productReviewService.updateReview(review, UserStorage.getCurrentUser().getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        productReviewService.deleteReview(reviewId, UserStorage.getCurrentUser().getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public List<ProductReview> getProductReviewsByUserId() {
        return productReviewService.getProductReviewsByUserId(UserStorage.getCurrentUser().getId());
    }

    @PostMapping("/{reviewId}/helpful")
    public void markReviewHelpful(@PathVariable("reviewId") Integer reviewId) {
        productReviewService.markReviewHelpful(reviewId, UserStorage.getCurrentUser().getId());
    }

    @PostMapping("/{reviewId}/not-helpful")
    public void markReviewNotHelpful(@PathVariable("reviewId") Integer reviewId) {
        productReviewService.markReviewNotHelpful(reviewId, UserStorage.getCurrentUser().getId());
    }
}