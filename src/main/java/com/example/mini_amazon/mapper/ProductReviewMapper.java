package com.example.mini_amazon.mapper;



import com.example.mini_amazon.model.ProductReview;
import com.example.mini_amazon.model.UserReviewHelpfulness;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductReviewMapper {
    @Insert("INSERT INTO product_reviews(product_id, user_id, rating, comment) VALUES(#{productId}, #{userId}, #{rating}, #{comment})")
    void addReview(ProductReview review);

    @Select("SELECT * FROM product_reviews WHERE product_id = #{productId} and user_id = #{userId}")
    List<ProductReview> findByProductId(@Param("productId") Long productId, @Param("userId") Long userId);

    @Select("SELECT * FROM product_reviews WHERE user_id = #{userId}")
    List<ProductReview> findByUserId(Long userId);

    @Select("SELECT * FROM product_reviews WHERE id = #{id}")
    ProductReview findById(Long id);

    @Update("UPDATE product_reviews SET rating = #{rating}, comment = #{comment} WHERE id = #{id} and user_id = #{userId}")
    void updateReview(ProductReview review);

    @Delete("DELETE FROM product_reviews WHERE id = #{id} and user_id = #{userId}")
    void deleteReview(@Param("id") Long id, @Param("userId") Long userId);

    @Select("SELECT * FROM product_reviews WHERE product_id = #{productId} AND user_id = #{userId}")
    ProductReview getUserReviewForProduct(@Param("productId") Long productId, @Param("userId") Long userId);

    // 根据用户ID获取该用户的所有评论，并按产品名分组，每组内按点赞数降序和评论时间降序排序
    @Select("SELECT pr.*, COUNT(urh.is_helpful) AS helpfulnessCount " +
            "FROM product_reviews pr " +
            "LEFT JOIN user_review_helpfulness urh ON pr.id = urh.review_id " +
            "WHERE pr.user_id = #{userId} " +
            "GROUP BY pr.product_id, pr.id " +
            "ORDER BY pr.product_id, helpfulness_count DESC, pr.review_time DESC")
    List<ProductReview> getProductReviewsByUserId(@Param("userId") Long userId);

    // 更新评论的点赞数（增加或减少）
    @Update("UPDATE product_reviews SET helpfulness_count = helpfulness_count + #{countDelta} WHERE id = #{reviewId}")
    void updateHelpfulnessCount(@Param("reviewId") Integer reviewId, @Param("countDelta") Integer countDelta);
    // 根据评论ID和用户ID查询用户对该评论的帮助度标记情况

    @Select("SELECT * FROM user_review_helpfulness WHERE review_id = #{reviewId} AND user_id = #{userId}")
    UserReviewHelpfulness getUserReviewHelpfulness(@Param("reviewId") Integer reviewId, @Param("userId") Long userId);
    // 插入用户对评论的帮助度标记记录

    @Insert("INSERT INTO user_review_helpfulness (user_id, review_id, is_helpful) VALUES (#{userId}, #{reviewId}, #{isHelpful})")
    void insertUserReviewHelpfulness(UserReviewHelpfulness userReviewHelpfulness);
    // 根据评论ID和用户ID删除用户对该评论的帮助度标记记录（用于用户改变标记时）

    @Delete("DELETE FROM user_review_helpfulness WHERE review_id = #{reviewId} AND user_id = #{userId}")
    void deleteUserReviewHelpfulness(@Param("reviewId") Integer reviewId, @Param("userId") Long userId);

    @Update("UPDATE user_review_helpfulness SET is_helpful = #{isHelpful} WHERE review_id = #{reviewId} AND user_id = #{userId}")
    void updateUserReviewHelpfulness(UserReviewHelpfulness newMark);

    @Select("SELECT * FROM user_review_helpfulness WHERE user_id = #{userId}")
    List<UserReviewHelpfulness> getCurrentUserReviewHelpfulness(Long userId);
}