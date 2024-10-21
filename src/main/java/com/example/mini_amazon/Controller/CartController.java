package com.example.mini_amazon.Controller;


import com.example.mini_amazon.Service.CartService;
import com.example.mini_amazon.model.Cart;
import com.example.mini_amazon.model.CartItem;
import com.example.mini_amazon.model.CartItemDTO;
import com.example.mini_amazon.model.UserStorage;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    // 获取用户购物车中的商品项
    @GetMapping
    public List<CartItemDTO> getCartItems(@RequestParam Long userId) {
        return cartService.getCartItems(UserStorage.getCurrentUser().getId());
    }

    // 更新购物车中商品的数量
    @PostMapping("/items")
    public ResponseEntity<Void> addCartItem(@RequestBody CartItemDTO cartItemDTO) {
        // 将 DTO 转换为实体（如果需要）
        CartItem cartItem = new CartItem();
        cartItem.setProductId(cartItemDTO.getProductId());
        cartItem.setSellerId(cartItemDTO.getSellerId());
        cartItem.setQuantity(cartItemDTO.getQuantity());

        // 调用服务层的方法来添加购物车项
        cartService.addCartItem(cartItem);

        return ResponseEntity.ok().build();
    }

    // 更新购物车中商品的数量
    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<Void> updateCartItemQuantity(@PathVariable Long cartItemId, @RequestBody Map<String, Integer> body) {
        cartService.updateCartItemQuantity(cartItemId, body.get("quantity"));
        return ResponseEntity.ok().build();
    }

    // 删除购物车中的商品项
    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long cartItemId) {
        cartService.removeCartItem(cartItemId);
        return ResponseEntity.ok().build();
    }

    // 清空用户的购物车
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestParam Long userId) {
        cartService.clearCart(UserStorage.getCurrentUser().getId());
        return ResponseEntity.ok().build();
    }
}