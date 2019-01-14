package com.fedorizvekov.websocket.server.spring.handler;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@ExtendWith(MockitoExtension.class)
class WebSocketServerHandlerTest {

    @InjectMocks
    private WebSocketServerHandler handler;

    @Mock
    private WebSocketSession session;
    @Mock
    private HttpHeaders httpHeaders;

    @Captor
    private ArgumentCaptor<TextMessage> textMessageCaptor;


    @DisplayName("Should send connected message")
    @Test
    void shouldSend_connectedMessage() throws Exception {
        when(session.getHandshakeHeaders()).thenReturn(httpHeaders);
        when(session.getUri()).thenReturn(new URI("ws://localhost:8080/messenger?username=testUser"));

        handler.afterConnectionEstablished(session);

        verify(session).sendMessage(textMessageCaptor.capture());
        assertThat(textMessageCaptor.getValue().getPayload()).isEqualTo("testUser: CONNECTED");
    }


    @DisplayName("Should receive url params and send text message")
    @Test
    void shouldReceive_urlParams_andSendTextMessage() throws Exception {
        var field = WebSocketServerHandler.class.getDeclaredField("sessions");
        field.setAccessible(true);
        field.set(handler, singletonMap("testUser", session));

        when(session.getHandshakeHeaders()).thenReturn(httpHeaders);
        when(session.getUri()).thenReturn(new URI("ws://localhost:8080/messenger?username=testUser"));

        handler.handleTextMessage(session, new TextMessage("Test message"));

        verify(session).sendMessage(textMessageCaptor.capture());
        assertThat(textMessageCaptor.getValue().getPayload()).isEqualTo("testUser: Test message");
    }


    @DisplayName("Should receive header params and send text message")
    @Test
    void shouldReceive_headerParams_andSendTextMessage() throws Exception {
        var field = WebSocketServerHandler.class.getDeclaredField("sessions");
        field.setAccessible(true);
        field.set(handler, singletonMap("testUser", session));

        when(session.getHandshakeHeaders()).thenReturn(httpHeaders);
        when(httpHeaders.containsKey("username")).thenReturn(true);
        when(httpHeaders.getFirst("username")).thenReturn("testUser");

        handler.handleTextMessage(session, new TextMessage("Test message"));

        verify(session).sendMessage(textMessageCaptor.capture());
        assertThat(textMessageCaptor.getValue().getPayload()).isEqualTo("testUser: Test message");
    }


    @DisplayName("Should send disconnected message")
    @Test
    void shouldSend_disconnectedMessage() throws Exception {
        Map<String, WebSocketSession> sessions = new HashMap<>() {{
            put("testUser", session);
            put("testUser2", session);
        }};

        var field = WebSocketServerHandler.class.getDeclaredField("sessions");
        field.setAccessible(true);
        field.set(handler, sessions);

        when(session.getHandshakeHeaders()).thenReturn(httpHeaders);
        when(session.getUri()).thenReturn(new URI("ws://localhost:8080/messenger?username=testUser"));

        handler.afterConnectionClosed(session, CloseStatus.NORMAL);

        verify(session).sendMessage(textMessageCaptor.capture());
        assertThat(textMessageCaptor.getValue().getPayload()).isEqualTo("testUser: DISCONNECTED");
    }

}
