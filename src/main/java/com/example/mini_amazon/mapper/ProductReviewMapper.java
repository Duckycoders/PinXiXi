package com.example.mini_amazon.mapper;



import com.example.mini_amazon.model.ProductReview;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductReviewMapper {
    @Insert("INSERT INTO product_reviews(product_id, user_id, rating, comment) VALUES(#{productId}, #{userId}, #{rating}, #{comment})")
    void addReview(ProductReview review);

    @Select("SELECT * FROM product_reviews WHERE product_id = #{productId}")
    List<ProductReview> findByProductId(Long productId);

    @Select("SELECT * FROM product_reviews WHERE user_id = #{userId}")
    List<ProductReview> findByUserId(Long userId);

    @Select("SELECT * FROM product_reviews WHERE id = #{id}")
    ProductReview findById(Long id);

    @Update("UPDATE product_reviews SET rating = #{rating}, comment = #{comment} WHERE id = #{id}")
    void updateReview(ProductReview review);

    @Delete("DELETE FROM product_reviews WHERE id = #{id}")
    void deleteReview(Long id);

    @Select("SELECT * FROM product_reviews WHERE product_id = #{productId} AND user_id = #{userId}")
    ProductReview getUserReviewForProduct(@Param("productId") Long productId, @Param("userId") Long userId);
}