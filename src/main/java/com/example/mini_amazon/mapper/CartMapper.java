package com.example.mini_amazon.mapper;

import com.example.mini_amazon.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    // 获取指定用户的购物车
    @Select("SELECT * FROM carts WHERE user_id = #{userId}")
    Cart getCartByUserId(@Param("userId") Long userId);

    // 获取购物车中的商品项列表
    @Select("SELECT ci.*, p.name as productName, p.price FROM cart_items ci " +
            "JOIN products p ON ci.product_id = p.id WHERE ci.cart_id = #{cartId} and ci.is_save_for_later = #{isSaveForLater}")
    List<CartItemDTO> getCartItemsByCartId(@Param("cartId") Long cartId, @Param("isSaveForLater") boolean isSaveForLater);

    // 添加商品到购物车
    @Insert("INSERT INTO cart_items (cart_id, product_id, seller_id, quantity) VALUES (#{cartId}, #{productId}, #{sellerId}, #{quantity})")
    void addCartItem(CartItem cartItem);

    // 更新购物车中的商品数量
    @Update("UPDATE cart_items SET quantity = #{quantity} WHERE id = #{cartItemId}")
    void updateCartItemQuantity(@Param("cartItemId") Long cartItemId, @Param("quantity") int quantity);

    // 删除购物车中的商品项
    @Delete("DELETE FROM cart_items WHERE id = #{cartItemId}")
    void deleteCartItem(@Param("cartItemId") Long cartItemId);

    // 清空购物车中的所有商品
    @Delete("DELETE FROM cart_items WHERE cart_id = #{cartId}")
    void clearCartItems(@Param("cartId") Long cartId);

    // 创建订单
    @Insert("INSERT INTO orders (user_id, total_amount, item_count, status) VALUES (#{userId}, #{totalAmount}, #{itemCount}, 'pending')")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createOrder(Order order);

    // 创建订单中的商品项
    @Insert("INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (#{orderId}, #{productId}, #{quantity}, #{price})")
    void createOrderItem(OrderItem orderItem);

    @Delete("DELETE FROM cart_items WHERE product_id = #{productId}")
    void deleteCartItemByProductId(@Param("productId") Integer productId);

    @Insert("INSERT INTO carts (user_id) VALUES (#{userId})")
    void createCart(@Param("userId") Long userId);

    // 将商品设置为保存以备以后或添加回购物车（根据传入的操作类型）
    @Update("UPDATE cart_items SET is_save_for_later = #{isSaveForLater} WHERE id = #{cartItemId}")
    void updateSaveForLaterStatus(@Param("cartItemId") Integer cartItemId, @Param("isSaveForLater") boolean isSaveForLater);
}