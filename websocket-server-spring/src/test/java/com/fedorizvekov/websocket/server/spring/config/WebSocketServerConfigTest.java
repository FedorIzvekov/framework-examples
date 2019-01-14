package com.fedorizvekov.websocket.server.spring.config;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.concurrent.CountDownLatch;
import com.fedorizvekov.websocket.server.spring.handler.WebSocketServerHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketServerConfigTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebSocketServerHandler websocketServerHandler;


    @DisplayName("Should connect to WebSocket and receive messages")
    @Test
    public void ShouldConnect_toWebSocket_andReceiveMessages() throws Exception {
        String url = "ws://localhost:" + port + "/messenger?username=test_user";
        CountDownLatch latch = new CountDownLatch(2);
        TestWebSocketHandler testHandler = new TestWebSocketHandler(latch);

        var session = new StandardWebSocketClient().doHandshake(testHandler, url).get();
        session.sendMessage(new TextMessage("Test message"));

        assertAll(
                () -> assertThat(latch.await(5, SECONDS)).isTrue(),
                () -> assertThat(testHandler.getReceivedMessages()).hasSize(2),
                () -> assertThat(testHandler.getReceivedMessages().get(0)).isEqualTo("test_user: CONNECTED"),
                () -> assertThat(testHandler.getReceivedMessages().get(1)).isEqualTo("test_user: Test message")
        );

        session.close();
    }

}
