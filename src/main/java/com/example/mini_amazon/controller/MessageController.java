package com.example.mini_amazon.controller;


import com.example.mini_amazon.service.MessageService;
import com.example.mini_amazon.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    public void sendMessage(@RequestBody Message message) {
        messageService.sendMessage(message);
    }

    @GetMapping
    public List<Message> getMessages(@RequestParam Integer userId, @RequestParam Integer sellerId) {
        return messageService.getMessages(userId, sellerId);
    }
}