package com.example.mini_amazon.mapper;



import com.example.mini_amazon.model.Category;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    // Table name should be lower case 'categories'
    // Using capital 'Categories' will fail on case-sensitive databases
    @Select("SELECT * FROM categories")
    List<Category> findAll();
}

