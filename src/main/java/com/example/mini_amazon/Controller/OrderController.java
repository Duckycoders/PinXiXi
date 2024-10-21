package com.example.mini_amazon.Controller;


import com.example.mini_amazon.Service.OrderService;
import com.example.mini_amazon.model.Order;
import com.example.mini_amazon.model.OrderItem;
import com.example.mini_amazon.model.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // @PostMapping
    // public void createOrder(@RequestBody Order order) {
    //     orderService.createOrder(order);
    // }

    @GetMapping("/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Integer userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/{orderId}/items")
    public List<OrderItem> getOrderItemsByOrderId(@PathVariable Integer orderId) {
        return orderService.getOrderItemsByOrderId(orderId);
    }

    // 提交订单
    @PostMapping
    public ResponseEntity<Void> submitOrder(@RequestParam Long userId) {
        try {
            orderService.submitOrder(UserStorage.getCurrentUser().getId());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}