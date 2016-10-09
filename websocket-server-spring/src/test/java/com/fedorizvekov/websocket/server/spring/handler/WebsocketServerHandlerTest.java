package com.fedorizvekov.websocket.server.spring.handler;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@RunWith(MockitoJUnitRunner.class)
public class WebsocketServerHandlerTest {

    @InjectMocks
    private WebsocketServerHandler handler;

    @Mock
    private WebSocketSession session;
    @Mock
    private HttpHeaders httpHeaders;

    @Captor
    private ArgumentCaptor<TextMessage> textMessageCaptor;


    @Test
    public void should_send_connectedMessage() throws Exception {
        when(session.getHandshakeHeaders()).thenReturn(httpHeaders);
        when(session.getUri()).thenReturn(new URI("ws://localhost:8080/messenger?username=testUser"));

        handler.afterConnectionEstablished(session);

        verify(session).sendMessage(textMessageCaptor.capture());
        assertThat(textMessageCaptor.getValue().getPayload()).isEqualTo("testUser: CONNECTED");
    }


    @Test
    public void should_receive_url_params_and_send_textMessage() throws Exception {
        Whitebox.setInternalState(handler, "sessions", singletonMap("testUser", session));
        when(session.getHandshakeHeaders()).thenReturn(httpHeaders);
        when(session.getUri()).thenReturn(new URI("ws://localhost:8080/messenger?username=testUser"));

        handler.handleTextMessage(session, new TextMessage("Test message"));

        verify(session).sendMessage(textMessageCaptor.capture());
        assertThat(textMessageCaptor.getValue().getPayload()).isEqualTo("testUser: Test message");
    }


    @Test
    public void should_receive_header_params_and_send_textMessage() throws Exception {
        Whitebox.setInternalState(handler, "sessions", singletonMap("testUser", session));
        when(session.getHandshakeHeaders()).thenReturn(httpHeaders);
        when(httpHeaders.containsKey("username")).thenReturn(true);
        when(httpHeaders.getFirst("username")).thenReturn("testUser");
        when(session.getUri()).thenReturn(new URI("ws://localhost:8080/messenger"));

        handler.handleTextMessage(session, new TextMessage("Test message"));

        verify(session).sendMessage(textMessageCaptor.capture());
        assertThat(textMessageCaptor.getValue().getPayload()).isEqualTo("testUser: Test message");
    }


    @Test
    public void should_send_disconnectedMessage() throws Exception {
        Map<String, WebSocketSession> sessions = new HashMap() {{
            put("testUser", session);
            put("testUser2", session);
        }};
        Whitebox.setInternalState(handler, "sessions", sessions);
        when(session.getHandshakeHeaders()).thenReturn(httpHeaders);
        when(session.getUri()).thenReturn(new URI("ws://localhost:8080/messenger?username=testUser"));

        handler.afterConnectionClosed(session, CloseStatus.NORMAL);

        verify(session).sendMessage(textMessageCaptor.capture());
        assertThat(textMessageCaptor.getValue().getPayload()).isEqualTo("testUser: DISCONNECTED");
    }

}
