package com.fedorizvekov.websocket.server.spring.handler;

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
public class WebsocketServerHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new HashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userName = getUserName(session);
        sessions.put(userName, session);

        broadcast(userName + ": CONNECTED");
    }


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String userName = getUserName(session);

        broadcast(userName + ": " + message.getPayload());
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userName = getUserName(session);
        sessions.remove(userName);

        broadcast(userName + ": DISCONNECTED");
    }


    private void broadcast(String message) {
        log.info(message);
        TextMessage textMessage = new TextMessage(message);

        for (WebSocketSession session : sessions.values()) {

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
            return session.getUri().getQuery().split("=")[1];
        }

    }

}
