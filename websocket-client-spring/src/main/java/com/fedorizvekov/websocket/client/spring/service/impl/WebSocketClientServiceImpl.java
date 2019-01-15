package com.fedorizvekov.websocket.client.spring.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.fedorizvekov.websocket.client.spring.model.WebSocketSessionWrapper;
import com.fedorizvekov.websocket.client.spring.service.WebSocketClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketConnectionManager;

@Log4j2
@Service
@RequiredArgsConstructor
public class WebSocketClientServiceImpl implements WebSocketClientService {

    private final WebSocketConnectionManager connectionManager;


    public void runClient() {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {

            log.info("Enter username:");
            var username = reader.readLine().trim();
            log.info("Username: {}", username);

            var headers = new WebSocketHttpHeaders();
            headers.add("username", username);
            connectionManager.setHeaders(headers);
            connectionManager.start();

            while (true) {
                var message = reader.readLine();

                if ("exit".equalsIgnoreCase(message)) {
                    connectionManager.stop();
                    break;
                } else {

                    var session = WebSocketSessionWrapper.getSession();

                    if (session != null && session.isOpen()) {
                        session.sendMessage(new TextMessage(message));
                    }

                }
            }

        } catch (IOException exception) {
            log.error("IOException, because: {}", exception.getMessage());
            throw new RuntimeException("IOException, because: " + exception.getMessage());
        }
    }

}
