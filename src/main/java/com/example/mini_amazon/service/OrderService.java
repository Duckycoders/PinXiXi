package com.example.mini_amazon.service;


import com.example.mini_amazon.mapper.CartMapper;
import com.example.mini_amazon.mapper.OrderMapper;
import com.example.mini_amazon.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    public void createOrder(Order order) {
        orderMapper.createOrder(order);
    }

    public void addOrderItem(OrderItem item) {
        orderMapper.addOrderItem(item);
    }

    public List<OrderDTO> getOrdersByUserId(Long userId) {
        return orderMapper.findOrdersByUserId(userId);
    }

    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        return orderMapper.findOrderItemsByOrderId(orderId);
    }

    public List<OrderDTO> getSellerOrders(Long sellerId) {
        return orderMapper.getSellerOrders(sellerId);
    }

    public List<OrderItemDTO> getOrderItems(Long orderId) {
        return orderMapper.getOrderItems(orderId);
    }

    public void fulfillOrder(Long orderId, Long sellerId) {
        orderMapper.fulfillOrder(orderId, sellerId);
    }

    @Transactional
    public void submitOrder(Long userId) {
        Cart cart = cartMapper.getCartByUserId(userId);
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }

        List<CartItemDTO> cartItems = cartMapper.getCartItemsByCartId(cart.getId(), false);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        BigDecimal totalAmount = BigDecimal.ZERO;
        int itemCount = 0;

        for (CartItemDTO cartItem : cartItems) {
            totalAmount = totalAmount.add(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            itemCount += cartItem.getQuantity();
        }

        // 创建订单
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setItemCount(itemCount);
        cartMapper.createOrder(order);

        // 创建订单商品项
        for (CartItemDTO cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            cartMapper.createOrderItem(orderItem);
        }

        // 清空购物车
        cartMapper.clearCartItems(cart.getId());
    }

    public void markOrderComplete(Long orderId) {
        orderMapper.updateOrderStatus(orderId, "completed");
    }
}