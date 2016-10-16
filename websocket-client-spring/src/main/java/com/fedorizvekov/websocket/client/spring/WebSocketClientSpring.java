package com.fedorizvekov.websocket.client.spring;

import com.fedorizvekov.websocket.client.spring.service.WebSocketClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WebSocketClientSpring {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WebSocketClientSpring.class, args);
        WebSocketClientService clientService = context.getBean(WebSocketClientService.class);
        clientService.runClient();
        System.exit(SpringApplication.exit(context, () -> 0));
    }

}
