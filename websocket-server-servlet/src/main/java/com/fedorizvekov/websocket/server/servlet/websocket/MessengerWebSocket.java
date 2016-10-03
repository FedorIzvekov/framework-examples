package com.fedorizvekov.websocket.server.servlet.websocket;

import java.io.IOException;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@Log4j2
@WebSocket
public class MessengerWebSocket {

    private static final MessengerWebSocket webSocket = new MessengerWebSocket();
    private final Set<Session> webSocketSessions = new ConcurrentHashSet<>();

    private MessengerWebSocket() {
    }


    public static MessengerWebSocket getInstance() {
        return webSocket;
    }


    @OnWebSocketConnect
    public void onConnect(Session session) {
        webSocketSessions.add(session);

        String username = session.getUpgradeRequest().getParameterMap().get("username").get(0);
        broadcast(username + ": CONNECTED");
    }


    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        webSocketSessions.remove(session);

        String username = session.getUpgradeRequest().getParameterMap().get("username").get(0);
        broadcast(username + ": DISCONNECTED");
    }


    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
        String username = session.getUpgradeRequest().getParameterMap().get("username").get(0);
        broadcast(username + ": " + message);
    }


    private void broadcast(String message) {
        log.info(message);
        for (Session session : webSocketSessions) {
            try {
                session.getRemote().sendString(message);
            } catch (IOException exception) {
                log.error("Something went wrong, because: ", exception);
            }
        }
    }
}
