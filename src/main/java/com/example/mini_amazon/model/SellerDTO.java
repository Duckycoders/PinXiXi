package com.example.mini_amazon.model;

public class SellerDTO {
    private Long sellerId;
    private String name;
    private String email;
    private String address;

    // Constructors
    public SellerDTO() {}

    public SellerDTO(Long sellerId, String name, String email, String address) {
        this.sellerId = sellerId;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    // Getters and Setters
    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}