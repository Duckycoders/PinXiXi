package com.example.mini_amazon.Controller;


import com.example.mini_amazon.Service.ProductReviewService;
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
        return productReviewService.getProductReviews(productId);
    }

    @PostMapping("/product/{productId}")
    public ResponseEntity<Void> submitReview(@PathVariable Long productId, @RequestBody ProductReview review) {
        review.setProductId(productId);
        productReviewService.submitReview(review, UserStorage.getCurrentUser().getId());
        return ResponseEntity.ok().build();
    }

    // 删除评论方法同样去掉 userId 参数：
    @DeleteMapping("/product/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        productReviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }
}