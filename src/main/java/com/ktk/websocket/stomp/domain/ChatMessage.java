package com.ktk.websocket.stomp.domain;

import lombok.Data;

@Data
public class ChatMessage {
	private String type;
	private String content;
	private String sender;
}
