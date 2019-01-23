package com.fedorizvekov.http.client.spring.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.net.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    private final String url = "http://example.com";
    private final String requestBody = "{\"key\":\"value\"}";

    @InjectMocks
    private ClientServiceImpl service;

    @Mock
    private RestTemplate restTemplate;


    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(service, "url", url);
        ReflectionTestUtils.setField(service, "requestBody", requestBody);
    }


    @DisplayName("Should exchange rest template")
    @Test
    void shouldExchange_restTemplate() {
        when(restTemplate.exchange(any(RequestEntity.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>("Response body", CREATED));

        service.postRequest();

        verify(restTemplate).exchange(
                eq(RequestEntity.post(URI.create(url)).contentType(APPLICATION_JSON).body(requestBody)),
                eq(String.class)
        );
    }

}