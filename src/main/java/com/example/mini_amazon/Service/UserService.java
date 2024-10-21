package com.example.mini_amazon.Service;


import com.example.mini_amazon.mapper.UserMapper;
import com.example.mini_amazon.model.Purchase;
import com.example.mini_amazon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

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
}
