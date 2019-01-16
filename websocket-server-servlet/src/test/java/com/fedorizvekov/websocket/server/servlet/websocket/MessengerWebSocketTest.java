package com.fedorizvekov.websocket.server.servlet.websocket;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Set;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.UpgradeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MessengerWebSocketTest {

    @InjectMocks
    private MessengerWebSocket messengerWebSocket;

    @Mock
    private Session session;
    @Mock
    private UpgradeRequest upgradeRequest;
    @Mock
    private RemoteEndpoint remoteEndpoint;


    @BeforeEach
    void setUp() {
        when(session.getUpgradeRequest()).thenReturn(upgradeRequest);
        when(upgradeRequest.getParameterMap()).thenReturn(Map.of("username", singletonList("TestUserName")));
    }


    @DisplayName("Should connect session")
    @Test
    void shouldConnectSession() throws Exception {
        when(session.getRemote()).thenReturn(remoteEndpoint);

        messengerWebSocket.onConnect(session);

        assertThat(getPrivateSessions().size()).isEqualTo(1);
        verify(remoteEndpoint).sendString("TestUserName: CONNECTED");
    }


    @DisplayName("Should close session")
    @Test
    void shouldCloseSession() throws Exception {
        getPrivateSessions().add(session);

        messengerWebSocket.onClose(session, 1000, "Normal closure");

        assertThat(getPrivateSessions().size()).isZero();
        verify(remoteEndpoint, never()).sendString("TestUserName: DISCONNECTED");
    }


    @DisplayName("Should send message")
    @Test
    void shouldSendMessage() throws Exception {
        getPrivateSessions().add(session);
        when(session.getRemote()).thenReturn(remoteEndpoint);

        messengerWebSocket.onMessage(session, "Test Message");

        assertThat(getPrivateSessions().size()).isEqualTo(1);
        verify(remoteEndpoint).sendString("TestUserName: Test Message");
    }


    @SuppressWarnings("unchecked")
    private Set<Session> getPrivateSessions() throws Exception {
        var field = MessengerWebSocket.class.getDeclaredField("webSocketSessions");
        field.setAccessible(true);
        return (Set<Session>) field.get(messengerWebSocket);
    }

}
