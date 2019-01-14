package com.fedorizvekov.websocket.server.spring.handler;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Log4j2
@NoArgsConstructor
public class WebSocketServerHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new HashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        var userName = getUserName(session);
        sessions.put(userName, session);
        broadcast(userName + ": CONNECTED");
    }


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        var userName = getUserName(session);
        broadcast(userName + ": " + message.getPayload());
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        var userName = getUserName(session);
        sessions.remove(userName);
        broadcast(userName + ": DISCONNECTED");
    }


    private void broadcast(String message) {
        log.info(message);
        var textMessage = new TextMessage(message);

        for (var session : sessions.values()) {

            try {
                session.sendMessage(textMessage);

            } catch (IOException exception) {
                log.error("Something went wrong, because: ", exception);
            }

        }

    }


    private String getUserName(WebSocketSession session) {

        if (session.getHandshakeHeaders().containsKey("username")) {
            return session.getHandshakeHeaders().getFirst("username");
        } else {
            return requireNonNull(session.getUri()).getQuery().split("=")[1];
        }

    }

}
