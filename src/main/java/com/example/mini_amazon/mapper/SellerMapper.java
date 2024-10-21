package com.example.mini_amazon.mapper;

import com.example.mini_amazon.model.Seller;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SellerMapper {

    // 插入卖家
    @Insert("INSERT INTO sellers (name, email, address, user_id) VALUES (#{name}, #{email}, #{address}, #{userId})")
    int insertSeller(Seller seller);

    // 根据 ID 查询卖家
    @Select("SELECT * FROM sellers WHERE id = #{id}")
    Seller getSellerById(int id);

    // 更新卖家信息
    @Update("UPDATE sellers SET name = #{name}, email = #{email}, address = #{address} WHERE id = #{id}")
    int updateSeller(Seller seller);

    // 删除卖家
    @Delete("DELETE FROM sellers WHERE id = #{id}")
    int deleteSeller(int id);

    // 查询所有卖家
    @Select("SELECT * FROM sellers")
    List<Seller> getAllSellers();
}