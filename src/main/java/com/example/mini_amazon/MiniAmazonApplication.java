package com.example.mini_amazon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mini_amazon.mapper")
public class MiniAmazonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniAmazonApplication.class, args);
    }

}