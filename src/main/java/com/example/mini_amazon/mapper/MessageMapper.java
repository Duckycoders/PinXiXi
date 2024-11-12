package com.example.mini_amazon.mapper;


import com.example.mini_amazon.model.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Insert("INSERT INTO messages(user_id, seller_id, message) VALUES(#{userId}, #{sellerId}, #{message})")
    void sendMessage(Message message);

    @Select("SELECT * FROM messages WHERE user_id = #{userId} AND seller_id = #{sellerId} ORDER BY created_at")
    List<Message> getMessages(Integer userId, Integer sellerId);
}
