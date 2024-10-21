package com.example.mini_amazon.mapper;


import com.example.mini_amazon.model.Purchase;
import com.example.mini_amazon.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO users(email, name, address, password) VALUES(#{email}, #{name}, #{address}, #{password})")
    void register(User user);

    @Select("SELECT * FROM users WHERE email = #{email} AND password = #{password}")
    User login(@Param("email") String email, @Param("password") String password);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(Integer id);

    @Update("UPDATE users SET email=#{email}, name=#{name}, address=#{address} WHERE id=#{id}")
    void updateUser(User user);

    @Select("SELECT * FROM purchases WHERE user_id = #{userId} ORDER BY purchase_date DESC")
    List<Purchase> getPurchaseHistory(Integer userId);
}