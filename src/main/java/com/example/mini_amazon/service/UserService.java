package com.example.mini_amazon.service;


import com.example.mini_amazon.enums.ENHelpfulType;
import com.example.mini_amazon.mapper.ProductReviewMapper;
import com.example.mini_amazon.mapper.UserMapper;
import com.example.mini_amazon.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductReviewMapper productReviewMapper;

    public void register(User user) {
        userMapper.register(user);
    }

    public User login(String email, String password) {
        return userMapper.login(email, password);
    }

    public User getUserById(Integer id) {
        return userMapper.findById(id);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public List<Purchase> getPurchaseHistory(Integer userId) {
        return userMapper.getPurchaseHistory(userId);
    }

    public List<UserReviewDTO> getUserReviews() {
        return userMapper.findReviewsByUserId(UserStorage.getCurrentUser().getId());
    }

    public List<UserReviewDTO> getAllUserReviews() {
        List<UserReviewDTO> allUserReviews = userMapper.getAllUserReviews();
        List<UserReviewHelpfulness> helpfulnessList = productReviewMapper.getCurrentUserReviewHelpfulness(UserStorage.getCurrentUser().getId());
        Map<Integer, Boolean> helpfulnessMap = helpfulnessList.stream().collect(Collectors.toMap(UserReviewHelpfulness::getReviewId, UserReviewHelpfulness::getIsHelpful));
        allUserReviews.forEach(review -> {
            if (helpfulnessMap.get(review.getId()) != null) {
                review.setHelpfulType(helpfulnessMap.get(review.getId()) ? ENHelpfulType.HELP_FUL.getValue() : ENHelpfulType.UN_HELP_FUL.getValue());
            } else {
                review.setHelpfulType(ENHelpfulType.NO_RECORD.getValue());
            }
        });
        return allUserReviews;
    }

    public Long getCurrentId() {
        return userMapper.getCurrentId();
    }
}