package com.fedorizvekov.websocket.server.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebSocketServerSpringTest {

    @DisplayName("Should context loads")
    @Test
    void shouldContextLoads() {
        WebSocketServerSpring.main(new String[]{});
    }

}
