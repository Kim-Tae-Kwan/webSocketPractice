package com.ktk.websocket.stomp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.ktk.websocket.stomp.domain.ChatMessage;

@Controller
public class ChatController {
	@MessageMapping("/chat.sendMessage")
	@SendTo("/sub/javainuse")
	public ChatMessage sendMessage(@Payload ChatMessage webSocketChatMessage) {
		return webSocketChatMessage;
	}

	@MessageMapping("/chat.newUser")
	@SendTo("/sub/javainuse")
	public ChatMessage newUser(@Payload ChatMessage webSocketChatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", webSocketChatMessage.getSender());
		return webSocketChatMessage;
	}
}
