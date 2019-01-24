package com.fedorizvekov.http.server.servlet.servlet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServletTest {

    private final StringBuffer endpoint = new StringBuffer("/test");

    @InjectMocks
    private UserServlet userServlet;

    @Mock
    private HttpServletRequest servletRequest;
    @Mock
    private HttpServletResponse servletResponse;
    private StringWriter resultWriter;


    @DisplayName("Should return SC_CREATED and user data")
    @Test
    void shouldReturn_SC_CREATED_andUserData() throws Exception {
        when(servletRequest.getRequestURL()).thenReturn(endpoint);
        when(servletRequest.getReader()).thenReturn(new BufferedReader(new StringReader("{\"email\": \"testFirstName@email.com\", \"firstName\": \"testFirstName\", \"lastName\": \"testLastName\"}")));
        resultWriter = new StringWriter();
        when(servletResponse.getWriter()).thenReturn(new PrintWriter(resultWriter));

        userServlet.doPost(servletRequest, servletResponse);

        verify(servletResponse).setStatus(HttpServletResponse.SC_CREATED);
        assertThat(resultWriter.toString()).isEqualTo("UserDto(email=testFirstName@email.com, firstName=testFirstName, lastName=testLastName)");
    }


    @DisplayName("Should return SC_CREATED and empty user data")
    @Test
    void shouldReturn_SC_CREATED_and_emptyUserData() throws Exception {
        when(servletRequest.getRequestURL()).thenReturn(endpoint);
        when(servletRequest.getReader()).thenReturn(new BufferedReader(new StringReader("{\"unknownParameter\": \"unknownValue\"}")));
        resultWriter = new StringWriter();
        when(servletResponse.getWriter()).thenReturn(new PrintWriter(resultWriter));

        userServlet.doPost(servletRequest, servletResponse);

        verify(servletResponse).setStatus(HttpServletResponse.SC_CREATED);
        assertThat(resultWriter.toString()).isEqualTo("UserDto(email=null, firstName=null, lastName=null)");
    }


    @DisplayName("Should return SC_BAD_REQUEST")
    @Test
    void shouldReturn_SC_BAD_REQUEST() throws Exception {
        when(servletRequest.getRequestURL()).thenReturn(endpoint);
        when(servletRequest.getReader()).thenReturn(new BufferedReader(new StringReader("Bad request")));

        userServlet.doPost(servletRequest, servletResponse);

        verify(servletResponse).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }


    @DisplayName("Should return SC_INTERNAL_SERVER_ERROR")
    @Test
    void shouldReturn_SC_INTERNAL_SERVER_ERROR() throws Exception {
        when(servletRequest.getRequestURL()).thenReturn(endpoint);
        when(servletRequest.getReader()).thenThrow(new IOException("Something went wrong"));

        userServlet.doPost(servletRequest, servletResponse);

        verify(servletResponse).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

}
