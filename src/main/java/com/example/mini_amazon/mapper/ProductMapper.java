package com.example.mini_amazon.mapper;


import com.example.mini_amazon.model.Product;
import com.example.mini_amazon.model.ProductDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM products WHERE category_id = #{categoryId} ORDER BY price ASC")
    List<Product> findByCategory(Integer categoryId);

    @Select("SELECT * FROM products WHERE name LIKE CONCAT('%', #{keyword}, '%') OR description LIKE CONCAT('%', #{keyword}, '%')")
    List<Product> searchByKeyword(String keyword);

    @Select("SELECT * FROM products ORDER BY price")
    List<Product> findAll();

    @Insert("INSERT INTO products(id, name, description, image_url, price, category_id, seller_id) VALUES(#{id}, #{name}, #{description}, #{imageUrl}, #{price}, #{categoryId}, #{sellerId})")
    void addProduct(Product product);

    @Select("SELECT * FROM products WHERE id = #{id}")
    Product findById(Integer id);

    @Delete("DELETE FROM products WHERE id = #{id}")
    void deleteProduct(Integer id);

    @Update("UPDATE products SET name = #{name}, description = #{description}, image_url = #{imageUrl}, price = #{price}, category_id = #{categoryId} WHERE id = #{id}")
    void updateProduct(Product product);

    @Update("UPDATE product_inventory SET stock_quantity = #{quantity} WHERE product_id = #{productId}")
    void updateProductQuantity(@Param("productId") Long productId, @Param("quantity") Integer quantity);

    @Select("SELECT p.*, pi.stock_quantity as stockQuantity FROM products p, product_inventory pi WHERE p.id = pi.product_id and p.seller_id = #{sellerId}")
    List<ProductDTO> getProductsBySellerId(Long sellerId);

    @Insert("INSERT INTO product_inventory(product_id, seller_id, stock_quantity) VALUES(#{id}, #{sellerId}, #{quantity})")
    void addProductInventory(Product product);

    @Select("SELECT max(id) FROM products")
    Integer getCurrentId();
}