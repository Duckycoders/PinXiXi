package com.example.mini_amazon.service;


import com.example.mini_amazon.mapper.CartMapper;
import com.example.mini_amazon.model.Cart;
import com.example.mini_amazon.model.CartItem;
import com.example.mini_amazon.model.CartItemDTO;
import com.example.mini_amazon.model.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;

    // public List<Cart> getCartByUserId(Integer userId) {
    //     return cartMapper.findByUserId(userId);
    // }
    //
    // public void createCart(Integer userId) {
    //     cartMapper.createCart(userId);
    // }
    //
    // public void addCartItem(CartItem item) {
    //     cartMapper.addCartItem(item);
    // }
    //
    // public List<CartItem> getCartItems(Integer cartId) {
    //     return cartMapper.findCartItems(cartId);
    // }
    //
    // public void removeCartItem(Integer itemId) {
    //     cartMapper.removeCartItem(itemId);
    // }
    //
    // public void updateCartItemQuantity(Integer itemId, Integer quantity) {
    //     cartMapper.updateCartItemQuantity(itemId, quantity);
    // }

    // 获取指定用户的购物车商品项
    public List<CartItemDTO> getCartItems(Long userId, Boolean isSaveForLater) {
        Cart cart = cartMapper.getCartByUserId(userId);
        if (cart != null) {
            return cartMapper.getCartItemsByCartId(cart.getId(), isSaveForLater);
        }
        return new ArrayList<>();
    }

    // 更新购物车中商品的数量
    public void updateCartItemQuantity(Long cartItemId, int quantity) {
        cartMapper.updateCartItemQuantity(cartItemId, quantity);
    }

    // 删除购物车中的商品项
    public void removeCartItem(Long cartItemId) {
        cartMapper.deleteCartItem(cartItemId);
    }

    // 清空指定用户的购物车
    public void clearCart(Long userId) {
        Cart cart = cartMapper.getCartByUserId(userId);
        if (cart != null) {
            cartMapper.clearCartItems(cart.getId());
        }
    }

    public void addCartItem(CartItem cartItem) {
        Cart cart = cartMapper.getCartByUserId(UserStorage.getCurrentUser().getId());
        if (Objects.nonNull(cart)) {
            cartItem.setCartId(cart.getId());
        }
        cartMapper.addCartItem(cartItem);
    }

    public void addCart(Long id) {
        cartMapper.createCart(id);
    }

    public void saveForLaterOrAddToCart(Integer cartItemId, boolean isSaveForLater) {
        cartMapper.updateSaveForLaterStatus(cartItemId, isSaveForLater);
    }
}