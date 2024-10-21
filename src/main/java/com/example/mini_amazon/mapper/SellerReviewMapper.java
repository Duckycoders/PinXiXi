package com.example.mini_amazon.mapper;



import com.example.mini_amazon.model.SellerReview;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SellerReviewMapper {
    @Insert("INSERT INTO seller_reviews(seller_id, user_id, rating, comment) VALUES(#{sellerId}, #{userId}, #{rating}, #{comment})")
    void addReview(SellerReview review);

    @Select("SELECT * FROM seller_reviews WHERE seller_id = #{sellerId} ORDER BY created_at DESC")
    List<SellerReview> findBySellerId(Integer sellerId);

    @Select("SELECT * FROM seller_reviews WHERE user_id = #{userId} ORDER BY created_at DESC")
    List<SellerReview> findByUserId(Integer userId);

    @Select("SELECT * FROM seller_reviews WHERE id = #{id}")
    SellerReview findById(Integer id);

    @Update("UPDATE seller_reviews SET rating = #{rating}, comment = #{comment} WHERE id = #{id}")
    void updateReview(SellerReview review);

    @Delete("DELETE FROM seller_reviews WHERE id = #{id}")
    void deleteReview(Integer id);
}
