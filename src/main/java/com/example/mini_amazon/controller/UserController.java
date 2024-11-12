package com.example.mini_amazon.controller;

import com.example.mini_amazon.model.*;
import com.example.mini_amazon.service.CartService;
import com.example.mini_amazon.service.SellerService;
import com.example.mini_amazon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private CartService cartService;

    @PostMapping("/register")
    @Transactional
    public String register(User user, RedirectAttributes redirectAttributes) {
        try {
            long id = userService.getCurrentId() + 1;
            user.setId(id);
            userService.register(user);
            Seller seller = new Seller();
            seller.setName(user.getName());
            seller.setEmail(user.getEmail());
            seller.setAddress(user.getAddress());
            seller.setUserId(user.getId());
            sellerService.addSeller(seller);
            cartService.addCart(id);
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", "The username already exists！");
            return "redirect:/register";
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(User user, Model model) {
        User loginUser = userService.login(user.getEmail(), user.getPassword());
        if (loginUser != null) {
            // 存储登录用户信息
            UserStorage.getInstance().setCurrentUser(loginUser);
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
        return "/menu";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        UserStorage.removeCurrentUser();
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @GetMapping("/{id}/purchases")
    @ResponseBody
    public List<Purchase> getPurchaseHistory(@PathVariable Integer id) {
        return userService.getPurchaseHistory(id);
    }


    // 获取当前用户的所有评论
    @GetMapping("/reviews")
    @ResponseBody
    public List<UserReviewDTO> getUserReviews() {
        return userService.getUserReviews();
    }

    // 获取所有评论
    @GetMapping("/allReviews")
    @ResponseBody
    public List<UserReviewDTO> getAllUserReviews() {
        return userService.getAllUserReviews();
    }
}