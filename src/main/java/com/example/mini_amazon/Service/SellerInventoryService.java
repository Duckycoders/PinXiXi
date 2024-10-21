package com.example.mini_amazon.Service;


import com.example.mini_amazon.mapper.SellerInventoryMapper;
import com.example.mini_amazon.model.SellerInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerInventoryService {
    @Autowired
    private SellerInventoryMapper sellerInventoryMapper;

    public List<SellerInventory> getInventoryBySellerId(Integer sellerId) {
        return sellerInventoryMapper.findBySellerId(sellerId);
    }

    public void addInventory(SellerInventory inventory) {
        sellerInventoryMapper.addInventory(inventory);
    }

    public void updateStockQuantity(Integer id, Integer stockQuantity) {
        sellerInventoryMapper.updateStockQuantity(id, stockQuantity);
    }

    public void deleteInventory(Integer id) {
        sellerInventoryMapper.deleteInventory(id);
    }
}
