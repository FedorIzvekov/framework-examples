package com.fedorizvekov.http.server.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HttpServerSpringTest {

    @DisplayName("Should context loads")
    @Test
    void shouldContextLoads() {
        HttpServerSpring.main(new String[]{});
    }

}