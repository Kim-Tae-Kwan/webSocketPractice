package com.ktk.websocket.stomp;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.ktk.websocket.stomp.domain.ChatMessage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebSocketChatEventListener {
	
    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        System.out.println("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null) {

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType("Leave");
            chatMessage.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
            System.out.println("Leave user");
        }
    }
}
