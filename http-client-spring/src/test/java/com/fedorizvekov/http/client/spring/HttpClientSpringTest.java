package com.fedorizvekov.http.client.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HttpClientSpringTest {

    @DisplayName("Should context loads")
    @Test
    void shouldContextLoads() {
        HttpClientSpring.main(new String[]{});
    }

}