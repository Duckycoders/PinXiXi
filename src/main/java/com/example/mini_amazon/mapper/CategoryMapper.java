package com.example.mini_amazon.mapper;



import com.example.mini_amazon.model.Category;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("SELECT * FROM Categories")
    List<Category> findAll();
}

