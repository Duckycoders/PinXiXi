package com.example.mini_amazon.mapper;


import com.example.mini_amazon.model.Purchase;
import com.example.mini_amazon.model.User;
import com.example.mini_amazon.model.UserReviewDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO users(id, email, name, address, password) VALUES(#{id}, #{email}, #{name}, #{address}, #{password})")
    void register(User user);

    @Select("SELECT u.*, s.id as sellerId FROM users u, sellers s WHERE u.id = s.user_id and u.email = #{email} AND u.password = #{password}")
    User login(@Param("email") String email, @Param("password") String password);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(Integer id);

    @Update("UPDATE users SET email=#{email}, name=#{name}, address=#{address} WHERE id=#{id}")
    void updateUser(User user);

    @Select("SELECT * FROM purchases WHERE user_id = #{userId} ORDER BY purchase_date DESC")
    List<Purchase> getPurchaseHistory(Integer userId);

    @Select("SELECT COALESCE(max(id), 0) FROM users")
    Long getCurrentId();

    @Select("SELECT r.id, r.rating, r.comment, p.name AS product_name, r.review_time AS reviewTime FROM product_reviews r " +
            "JOIN products p ON r.product_id = p.id WHERE r.user_id = #{userId} order by r.review_time desc")
    List<UserReviewDTO> findReviewsByUserId(Long userId);

    @Select("SELECT pr.*, p.name AS productName FROM product_reviews pr " +
            "LEFT JOIN products p ON pr.product_id = p.id " +
            "GROUP BY pr.product_id, pr.id " +
            "ORDER BY pr.product_id, pr.helpfulness_count DESC, pr.review_time DESC")
    List<UserReviewDTO> getAllUserReviews();
}