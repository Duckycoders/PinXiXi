package com.example.mini_amazon.Service;

import com.example.mini_amazon.mapper.SellerInventoryMapper;
import com.example.mini_amazon.model.AddInventoryRequest;
import com.example.mini_amazon.model.InventoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private SellerInventoryMapper inventoryMapper;

    public List<InventoryDTO> getSellerInventory(Long sellerId) {
        return inventoryMapper.getSellerInventory(sellerId);
    }

    public void addInventory(AddInventoryRequest request) {
        inventoryMapper.addInventory(request);
    }

    public void updateStock(Long inventoryId, Long sellerId, int stockQuantity) {
        inventoryMapper.updateStock(inventoryId, sellerId, stockQuantity);
    }

    public void deleteInventory(Long inventoryId, Long sellerId) {
        inventoryMapper.deleteInventory(inventoryId, sellerId);
    }
}