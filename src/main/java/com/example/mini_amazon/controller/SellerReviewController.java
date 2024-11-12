package com.example.mini_amazon.controller;


import com.example.mini_amazon.service.SellerReviewService;
import com.example.mini_amazon.model.SellerReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers/reviews")
public class SellerReviewController {
    @Autowired
    private SellerReviewService sellerReviewService;

    @PostMapping
    public void addReview(@RequestBody SellerReview review) {
        sellerReviewService.addReview(review);
    }

    @GetMapping("/{sellerId}")
    public List<SellerReview> getReviews(@PathVariable Integer sellerId) {
        return sellerReviewService.getReviewsBySellerId(sellerId);
    }

    @PutMapping("/{id}")
    public void updateReview(@PathVariable Integer id, @RequestBody SellerReview review) {
        review.setId(id);
        sellerReviewService.updateReview(review);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Integer id) {
        sellerReviewService.deleteReview(id);
    }
}