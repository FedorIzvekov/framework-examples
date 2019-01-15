package com.fedorizvekov.websocket.client.spring.service.impl;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.client.WebSocketConnectionManager;

@SpringBootTest
public class WebSocketClientServiceImplTest {

    @Mock
    private WebSocketConnectionManager connectionManager;
    @Mock
    private InputStream inputStream;

    @Captor
    private ArgumentCaptor<HttpHeaders> captureHttpHeaders;

    @InjectMocks
    private WebSocketClientServiceImpl service;


    @DisplayName("Should start and stop connection")
    @Test
    void shouldStart_andStopConnection() throws Exception {
        doNothing().when(connectionManager).setHeaders(any(HttpHeaders.class));
        doNothing().when(connectionManager).start();
        System.setIn(new ByteArrayInputStream("test_user\ntest message\nexit\n".getBytes()));

        service.runClient();

        verify(connectionManager).setHeaders(captureHttpHeaders.capture());
        verify(connectionManager).start();
        verify(connectionManager).stop();

        assertThat(captureHttpHeaders.getValue())
                .containsKey("Username")
                .containsEntry("Username", singletonList("test_user"));
    }


    @DisplayName("Should handle IOException")
    @Test
    void shouldHandle_IOException() throws Exception {
        when(inputStream.read()).thenThrow(new IOException("IOException"));
        System.setIn(inputStream);

        assertThatThrownBy(() -> service.runClient())
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("IOException, because: Underlying input stream returned zero bytes");
    }

}
