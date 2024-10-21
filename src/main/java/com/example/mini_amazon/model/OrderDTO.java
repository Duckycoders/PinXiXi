package com.example.mini_amazon.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDTO {
    private Long id;
    private String buyerName;
    private String buyerAddress;
    private BigDecimal totalAmount;
    private int itemCount;
    private String status;
    private Date createdAt;

}