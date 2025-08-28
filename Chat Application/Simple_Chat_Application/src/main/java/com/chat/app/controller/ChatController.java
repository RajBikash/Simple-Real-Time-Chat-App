package com.chat.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.chat.app.model.ChatMessage;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Existing message sending
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        return message;
    }

    // NEW: Typing Indicator
    @MessageMapping("/typing")
    public void typing(Map<String, Object> typingData) {
        // typingData should contain: { "sender": "YourName", "typing": true/false }
        messagingTemplate.convertAndSend("/topic/typing", typingData);
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }
}
