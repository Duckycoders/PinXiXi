package com.example.mini_amazon.Controller;

import com.example.mini_amazon.Service.CartService;
import com.example.mini_amazon.Service.SellerService;
import com.example.mini_amazon.Service.UserService;
import com.example.mini_amazon.model.Purchase;
import com.example.mini_amazon.model.Seller;
import com.example.mini_amazon.model.User;
import com.example.mini_amazon.model.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private CartService cartService;

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        userService.register(user);
        Seller seller = new Seller();
        seller.setName(user.getName());
        seller.setEmail(user.getEmail());
        seller.setAddress(user.getAddress());
        seller.setUserId(user.getId());
        sellerService.addSeller(seller);
        cartService.addCart(user.getId());
    }

    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password) {
        User loginUser = userService.login(email, password);
        if (loginUser != null) {
            // 存储登录用户信息
            UserStorage.getInstance().setCurrentUser(loginUser);
        }
        return loginUser;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @GetMapping("/{id}/purchases")
    public List<Purchase> getPurchaseHistory(@PathVariable Integer id) {
        return userService.getPurchaseHistory(id);
    }
}