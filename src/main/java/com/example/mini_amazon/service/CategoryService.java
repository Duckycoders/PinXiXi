package com.example.mini_amazon.service;



import com.example.mini_amazon.mapper.CategoryMapper;
import com.example.mini_amazon.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getAllCategories() {
        return categoryMapper.findAll();
    }
}