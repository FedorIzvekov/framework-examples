package com.fedorizvekov.http.server.servlet.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import com.fedorizvekov.http.server.servlet.filter.CorsFilter;
import com.fedorizvekov.http.server.servlet.servlet.UserServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServerServiceImplTest {

    @InjectMocks
    private ServerServiceImpl serverService;

    @Mock
    private ServletContextHandler context;
    @Mock
    private Server server;

    @Captor
    private ArgumentCaptor<ServletHolder> captureServletHolder;
    @Captor
    private ArgumentCaptor<FilterHolder> captureFilterHolder;


    @DisplayName("should run server")
    @Test
    void shouldRunServer() throws Exception {
        doNothing().when(server).start();
        doNothing().when(server).setHandler(any(ServletContextHandler.class));
        doNothing().when(server).join();
        doNothing().when(context).setContextPath(anyString());
        doNothing().when(context).addServlet(any(ServletHolder.class), anyString());
        doNothing().when(context).addFilter(any(FilterHolder.class), anyString(), any(EnumSet.class));

        serverService.runServer();

        verify(context).setContextPath("/");
        verify(context).addServlet(captureServletHolder.capture(), eq("/users"));
        verify(context).addFilter(captureFilterHolder.capture(), eq("/*"), eq(EnumSet.of(DispatcherType.REQUEST)));
        verify(server).setHandler(context);
        verify(server).start();
        verify(server).join();

        assertAll(
                () -> assertThat(captureServletHolder.getValue().getServlet()).isInstanceOf(UserServlet.class),
                () -> assertThat(captureFilterHolder.getValue().getFilter()).isInstanceOf(CorsFilter.class)
        );
    }

}
