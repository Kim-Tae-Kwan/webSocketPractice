package com.ktk.websocket.basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.Getter;
import lombok.Setter;

@Component
public class SocketHandler extends TextWebSocketHandler {
	
	private List<WebSocketSession> sessions = new ArrayList<>();

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
		if(!sessions.contains(session)) {
			sessions.add(session);
		}
		
		String payload = (String) message.getPayload();
		JSONObject jsonObject = new JSONObject(payload);
		
		for(WebSocketSession s : sessions) {
			if(s.isOpen()) {
				s.sendMessage(new TextMessage(jsonObject.getString("message")));
			}else {
				sessions.remove(s);
			}
		}
	}
}
