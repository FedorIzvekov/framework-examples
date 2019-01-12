package com.fedorizvekov.websocket.client.spring;

import com.fedorizvekov.websocket.client.spring.service.WebSocketClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebSocketClientSpring {

    public static void main(String[] args) {
        var context = SpringApplication.run(WebSocketClientSpring.class, args);
        var clientService = context.getBean(WebSocketClientService.class);
        clientService.runClient();
        System.exit(SpringApplication.exit(context, () -> 0));
    }

}
