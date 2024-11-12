package com.example.mini_amazon.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mini_amazon.model.Product;
import com.example.mini_amazon.model.ProductDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    @Select("SELECT * FROM products WHERE category_id = #{categoryId} ORDER BY price ASC")
    List<Product> findByCategory(Integer categoryId);

    @Select("SELECT * FROM products WHERE name LIKE CONCAT('%', #{keyword}, '%') OR description LIKE CONCAT('%', #{keyword}, '%') LIMIT #{limit} OFFSET #{offset}")
    List<Product> searchByKeyword(@Param("keyword") String keyword, @Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT distinct p.*, pi.stock_quantity as quantity FROM products p, product_inventory pi where p.id = pi.product_id")
    List<Product> findAll();

    @Insert("INSERT INTO products(id, name, description, image_url, image, price, category_id, seller_id) VALUES(#{id}, #{name}, #{description}, #{imageUrl}, #{image}, #{price}, #{categoryId}, #{sellerId})")
    void addProduct(Product product);

    @Select("SELECT * FROM products WHERE id = #{id}")
    Product findById(Integer id);

    @Delete("DELETE FROM products WHERE id = #{id}")
    void deleteProduct(Integer id);

    @Update("UPDATE products SET name = #{name}, description = #{description}, image_url = #{imageUrl}, image = #{image}, price = #{price}, category_id = #{categoryId} WHERE id = #{id}")
    void updateProduct(Product product);

    @Update("UPDATE product_inventory SET stock_quantity = #{quantity} WHERE product_id = #{productId}")
    void updateProductQuantity(@Param("productId") Long productId, @Param("quantity") Integer quantity);

    @Select("SELECT p.*, pi.stock_quantity as stockQuantity FROM products p, product_inventory pi WHERE p.id = pi.product_id and p.seller_id = #{sellerId}")
    List<ProductDTO> getProductsBySellerId(Long sellerId);

    @Insert("INSERT INTO product_inventory(product_id, seller_id, stock_quantity) VALUES(#{id}, #{sellerId}, #{quantity})")
    void addProductInventory(Product product);

    @Update("UPDATE product_inventory SET stock_quantity = #{quantity} WHERE product_id = #{id}")
    void updateProductInventory(Product product);

    @Select("SELECT COALESCE(max(id), 0) FROM products")
    Integer getCurrentId();

    @Delete("DELETE FROM product_inventory WHERE product_id = #{id}")
    void deleteProductInventory(Integer id);

    List<Product> getAllProductIdsNotReview(Long id);

    List<Product> getAllProductIdsReviewed(Long id);

    @Delete("DELETE FROM product_reviews WHERE product_id = #{id}")
    void deleteProductReviews(Integer id);
}