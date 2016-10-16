package com.fedorizvekov.websocket.client.spring.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.WebSocketSession;

@Log4j2
public class WebSocketSessionWrapper {

    @Getter
    @Setter
    private static WebSocketSession session;


    public static void removeSession() {
        session = null;
    }

}
