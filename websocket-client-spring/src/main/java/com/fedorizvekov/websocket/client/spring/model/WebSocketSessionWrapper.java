package com.fedorizvekov.websocket.client.spring.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketSessionWrapper {

    @Getter
    @Setter
    private static WebSocketSession session;


    public static void removeSession() {
        session = null;
    }

}
