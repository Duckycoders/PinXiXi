package com.example.mini_amazon.Service;

import com.example.mini_amazon.mapper.MessageMapper;
import com.example.mini_amazon.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    public void sendMessage(Message message) {
        messageMapper.sendMessage(message);
    }

    public List<Message> getMessages(Integer userId, Integer sellerId) {
        return messageMapper.getMessages(userId, sellerId);
    }
}
