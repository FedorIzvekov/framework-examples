package com.fedorizvekov.websocket.client.spring.service.impl;

import com.fedorizvekov.websocket.client.spring.model.WebSocketSessionWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Log4j2
@Component
@RequiredArgsConstructor
public class TextHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        WebSocketSessionWrapper.setSession(session);
        log.info("Enter message or \"exit\"");
    }


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("RECEIVED: {}", message.getPayload());
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        WebSocketSessionWrapper.removeSession();
        log.info("DISCONNECTED");
    }

}
