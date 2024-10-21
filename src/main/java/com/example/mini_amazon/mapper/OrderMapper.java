package com.example.mini_amazon.mapper;


import com.example.mini_amazon.model.Order;
import com.example.mini_amazon.model.OrderDTO;
import com.example.mini_amazon.model.OrderItem;
import com.example.mini_amazon.model.OrderItemDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO orders(user_id, total_amount, status) VALUES(#{userId}, #{totalAmount}, #{status})")
    void createOrder(Order order);

    @Insert("INSERT INTO order_items(order_id, product_id, seller_id, quantity, price, fulfillment_status) VALUES(#{orderId}, #{productId}, #{sellerId}, #{quantity}, #{price}, #{fulfillmentStatus})")
    void addOrderItem(OrderItem item);

    @Select("SELECT * FROM orders WHERE user_id = #{userId}")
    List<Order> findOrdersByUserId(Integer userId);

    @Select("SELECT * FROM order_items WHERE order_id = #{orderId}")
    List<OrderItem> findOrderItemsByOrderId(Integer orderId);

    List<OrderDTO> getSellerOrders(@Param("sellerId") Long sellerId);

    List<OrderItemDTO> getOrderItems(@Param("orderId") Long orderId);

    void fulfillOrder(@Param("orderId") Long orderId, @Param("sellerId") Long sellerId);

    @Update("UPDATE orders SET status = #{completed} WHERE id = #{orderId}")
    void updateOrderStatus(@Param("orderId") Long orderId, @Param("completed") String completed);
}