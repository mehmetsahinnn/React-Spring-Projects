package com.humanresources.hr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/send/message")
    public void sendMessage(String message) {
        this.template.convertAndSend("/message",  message);
    }

    private final SimpMessagingTemplate template;
}
