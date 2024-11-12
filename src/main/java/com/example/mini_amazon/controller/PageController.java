package com.example.mini_amazon.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/", "/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/inventoryOrderManage")
    public String inventoryOrderManage() {
        return "inventoryOrderManage";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/productDetail")
    public String productDetail() {
        return "productDetail";
    }

    @GetMapping("/productReview")
    public String productReview() {
        return "productReview";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }

    @GetMapping("/sellerInventory")
    public String sellerInventory() {
        return "sellerInventory";
    }

    @GetMapping("/sellerReview")
    public String sellerReview() {
        return "sellerReview";
    }

    @GetMapping("/userReview")
    public String userReview() {
        return "userReview";
    }

    @GetMapping("/favorites")
    public String favorites() {
        return "favorites";
    }

    @GetMapping("/userOrder")
    public String userOrder() {
        return "userOrder";
    }
}