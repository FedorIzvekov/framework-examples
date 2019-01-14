package com.fedorizvekov.websocket.server.spring.config;

import com.fedorizvekov.websocket.server.spring.handler.WebSocketServerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketServerConfig implements WebSocketConfigurer {

    @Bean
    public WebSocketServerHandler getWebsocketServerHandler() {
        return new WebSocketServerHandler();
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(getWebsocketServerHandler(), "/messenger")
                .setAllowedOrigins("*");
    }

}
