package com.example.mini_amazon.mapper;


import com.example.mini_amazon.model.AddInventoryRequest;
import com.example.mini_amazon.model.InventoryDTO;
import com.example.mini_amazon.model.SellerInventory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SellerInventoryMapper {
    @Select("SELECT * FROM seller_inventory WHERE seller_id = #{sellerId}")
    List<SellerInventory> findBySellerId(Integer sellerId);

    @Insert("INSERT INTO seller_inventory(product_id, seller_id, stock_quantity) VALUES(#{productId}, #{sellerId}, #{stockQuantity})")
    void addInventory(SellerInventory inventory);

    @Update("UPDATE seller_inventory SET stock_quantity = #{stockQuantity} WHERE id = #{id}")
    void updateStockQuantity(@Param("id") Integer id, @Param("stockQuantity") Integer stockQuantity);

    @Delete("DELETE FROM seller_inventory WHERE id = #{id}")
    void deleteInventory(Integer id);

    List<InventoryDTO> getSellerInventory(@Param("sellerId") Long sellerId);

    void addInventory(AddInventoryRequest request);

    void updateStock(@Param("inventoryId") Long inventoryId, @Param("sellerId") Long sellerId, @Param("stockQuantity") int stockQuantity);

    void deleteInventory(@Param("inventoryId") Long inventoryId, @Param("sellerId") Long sellerId);
}