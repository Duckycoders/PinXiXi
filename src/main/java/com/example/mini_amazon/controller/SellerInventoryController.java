package com.example.mini_amazon.controller;


import com.example.mini_amazon.service.SellerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller/inventory")
public class SellerInventoryController {
    @Autowired
    private SellerInventoryService sellerInventoryService;

    // @GetMapping("/{sellerId}")
    // public List<SellerInventory> getInventory(@PathVariable Integer sellerId) {
    //     return sellerInventoryService.getInventoryBySellerId(sellerId);
    // }
    //
    // @PostMapping
    // public void addInventory(@RequestBody SellerInventory inventory) {
    //     sellerInventoryService.addInventory(inventory);
    // }
    //
    // @PutMapping("/{id}")
    // public void updateStock(@PathVariable Integer id, @RequestParam Integer stockQuantity) {
    //     sellerInventoryService.updateStockQuantity(id, stockQuantity);
    // }
    //
    // @DeleteMapping("/{id}")
    // public void deleteInventory(@PathVariable Integer id) {
    //     sellerInventoryService.deleteInventory(id);
    // }
}