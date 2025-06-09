package com.example.mini_amazon.service;

import com.example.mini_amazon.mapper.CartMapper;
import com.example.mini_amazon.model.Cart;
import com.example.mini_amazon.model.CartItem;
import com.example.mini_amazon.model.User;
import com.example.mini_amazon.model.UserStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartMapper cartMapper;

    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartService = new CartService();
        ReflectionTestUtils.setField(cartService, "cartMapper", cartMapper);
    }

    @Test
    void addCartItem_shouldAssignCartIdAndCallMapper() {
        User user = new User();
        user.setId(1L);
        UserStorage.getInstance().setCurrentUser(user);

        Cart cart = new Cart();
        cart.setId(2L);
        when(cartMapper.getCartByUserId(1L)).thenReturn(cart);

        CartItem cartItem = new CartItem();
        cartItem.setProductId(3L);
        cartItem.setSellerId(4L);
        cartItem.setQuantity(1);

        cartService.addCartItem(cartItem);

        assertEquals(2L, cartItem.getCartId());
        verify(cartMapper).addCartItem(cartItem);
    }

    @Test
    void clearCart_shouldRemoveItemsWhenCartExists() {
        Cart cart = new Cart();
        cart.setId(5L);
        when(cartMapper.getCartByUserId(1L)).thenReturn(cart);

        cartService.clearCart(1L);

        verify(cartMapper).clearCartItems(5L);
    }

    @Test
    void clearCart_shouldDoNothingWhenNoCart() {
        when(cartMapper.getCartByUserId(1L)).thenReturn(null);

        cartService.clearCart(1L);

        verify(cartMapper, never()).clearCartItems(anyLong());
    }
}
