package com.example.mini_amazon.Controller;

import com.example.mini_amazon.Service.InventoryService;
import com.example.mini_amazon.Service.OrderService;
import com.example.mini_amazon.Service.ProductService;
import com.example.mini_amazon.Service.SellerService;
import com.example.mini_amazon.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SellerService sellerService;

    @GetMapping("/search")
    public List<Seller> searchSeller() {
        return sellerService.findAllSellers();
    }

    @GetMapping("/inventory/{sellerId}")
    public List<InventoryDTO> getInventory(@PathVariable Long sellerId) {
        return inventoryService.getSellerInventory(sellerId);
    }

    @PostMapping("/inventory")
    public ResponseEntity<Void> addInventory(@RequestBody AddInventoryRequest request) {
        inventoryService.addInventory(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/inventory")
    public ResponseEntity<Void> updateStock(@RequestBody AddInventoryRequest request) {
        inventoryService.updateStock(request.getInventoryId(), request.getSellerId(), request.getStockQuantity());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/inventory")
    public ResponseEntity<Void> deleteInventory(@RequestBody AddInventoryRequest request) {
        inventoryService.deleteInventory(request.getInventoryId(), request.getSellerId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/orders/{sellerId}")
    public List<OrderDTO> getSellerOrders(@PathVariable Long sellerId) {
        return orderService.getSellerOrders(sellerId);
    }

    @GetMapping("/orders/{orderId}/items")
    public List<OrderItemDTO> getOrderItems(@PathVariable Long orderId) {
        return orderService.getOrderItems(orderId);
    }

    @PutMapping("/orders/{orderId}/fulfill")
    public ResponseEntity<Void> fulfillOrder(@PathVariable Long orderId,
                                             @RequestParam Long sellerId) {
        orderService.fulfillOrder(orderId, sellerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/orders/complete/{orderId}")
    public ResponseEntity<Void> markOrderComplete(@PathVariable Long orderId) {
        orderService.markOrderComplete(orderId);
        return ResponseEntity.ok().build();
    }
}