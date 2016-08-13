package com.fedorizvekov.websocket.server.servlet.servlet;

import com.fedorizvekov.websocket.server.servlet.websocket.MessengerWebSocket;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WebsocketMessengerServlet extends WebSocketServlet {
    private final static long LOGOUT_TIME_MS = 60 * 60 * 1000;

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME_MS);
        factory.setCreator((req, resp) -> MessengerWebSocket.getInstance());
    }

}
