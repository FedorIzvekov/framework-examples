package com.fedorizvekov.websocket.server.spring.config;

import com.fedorizvekov.websocket.server.spring.handler.WebsocketServerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketServerConfig implements WebSocketConfigurer {

    @Bean
    public WebsocketServerHandler getWebsocketServerHandler() {
        return new WebsocketServerHandler();
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(getWebsocketServerHandler(), "/messenger")
                .setAllowedOrigins("*");
    }

}
