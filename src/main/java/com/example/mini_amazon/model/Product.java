package com.example.mini_amazon.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@TableName("products")
public class Product {
    private Integer id;
    private String name;
    private String description;
    private String imageUrl;
    private byte[] image; // 存储图片数据的字段
    private Double price;
    @TableField(exist = false)
    private Integer quantity;
    private Integer categoryId;
    private Long sellerId;
}