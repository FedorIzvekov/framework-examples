package com.fedorizvekov.websocket.client.spring.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.fedorizvekov.websocket.client.spring.model.WebSocketSessionWrapper;
import com.fedorizvekov.websocket.client.spring.service.WebSocketClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;

@Log4j2
@Service
@RequiredArgsConstructor
public class WebSocketClientServiceImpl implements WebSocketClientService {

    private final WebSocketConnectionManager connectionManager;


    public void runClient() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            log.info("Enter username:");
            String username = reader.readLine().trim();
            log.info("Username: {}", username);

            WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
            headers.add("username", username);
            connectionManager.setHeaders(headers);
            connectionManager.start();

            while (true) {
                String message = reader.readLine();

                if ("exit".equalsIgnoreCase(message)) {
                    connectionManager.stop();
                    break;
                } else {

                    WebSocketSession session = WebSocketSessionWrapper.getSession();

                    if (session != null && session.isOpen()) {
                        session.sendMessage(new TextMessage(message));
                    }

                }
            }

        } catch (Exception exception) {
            log.error("Something went wrong, because: ", exception);
        }
    }

}
