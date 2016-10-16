package com.fedorizvekov.websocket.client.spring.config;

import com.fedorizvekov.websocket.client.spring.service.impl.TextHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@Configuration
@EnableWebSocket
public class WebSocketClientConfig {

    @Bean
    public StandardWebSocketClient getWebsocketClient() {
        return new StandardWebSocketClient();
    }


    @Bean
    public WebSocketConnectionManager getWebSocketConnectionManager(
            StandardWebSocketClient webSocketClient,
            TextHandler webSocketHandler,
            @Value("${websocket.server.url}") String url
    ) {
        return new WebSocketConnectionManager(webSocketClient, webSocketHandler, url);
    }

}
