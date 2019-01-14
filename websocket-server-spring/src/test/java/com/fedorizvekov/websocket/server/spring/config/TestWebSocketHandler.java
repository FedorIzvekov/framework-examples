package com.fedorizvekov.websocket.server.spring.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@RequiredArgsConstructor
class TestWebSocketHandler extends TextWebSocketHandler {

    private final CountDownLatch latch;
    @Getter
    private final List<String> receivedMessages = new ArrayList<>();


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        this.receivedMessages.add(message.getPayload());
        latch.countDown();
    }

}
