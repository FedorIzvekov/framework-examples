package com.fedorizvekov.websocket.server.servlet.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@Log4j2
@WebSocket
public class MessengerWebSocket {

    private static final MessengerWebSocket webSocket = new MessengerWebSocket();
    private final Set<Session> webSocketSessions = new HashSet<>();

    private MessengerWebSocket() {
    }


    public static MessengerWebSocket getInstance() {
        return webSocket;
    }


    @OnWebSocketConnect
    public void onConnect(Session session) {
        webSocketSessions.add(session);
        var username = getUserName(session);
        broadcast(username + ": CONNECTED");
    }


    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        webSocketSessions.remove(session);
        var username = getUserName(session);
        broadcast(username + ": DISCONNECTED");
    }


    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
        var username = getUserName(session);
        broadcast(username + ": " + message);
    }


    private void broadcast(String message) {
        log.info(message);
        for (var session : webSocketSessions) {
            try {
                session.getRemote().sendString(message);
            } catch (IOException exception) {
                log.error("Something went wrong, because: ", exception);
            }
        }
    }


    private String getUserName(Session session) {

        if (session.getUpgradeRequest().getHeaders().containsKey("username")) {

            return session.getUpgradeRequest().getHeader("username");

        } else {

            return session.getUpgradeRequest().getParameterMap().get("username").get(0);

        }

    }

}
