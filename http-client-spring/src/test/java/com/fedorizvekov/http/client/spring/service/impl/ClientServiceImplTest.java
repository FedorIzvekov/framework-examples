package com.fedorizvekov.http.client.spring.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.net.URI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    private final String url = "http://example.com";
    private final String requestBody = "{\"key\":\"value\"}";

    @InjectMocks
    private ClientServiceImpl service;

    @Mock
    private RestTemplate restTemplate;


    @Before
    public void setUp() {
        ReflectionTestUtils.setField(service, "url", url);
        ReflectionTestUtils.setField(service, "requestBody", requestBody);
    }


    @Test
    public void should_exchange_restTemplate() {
        ResponseEntity<String> mockResponse = new ResponseEntity<>("Response body", CREATED);
        when(restTemplate.exchange(any(RequestEntity.class), eq(String.class))).thenReturn(mockResponse);

        service.postRequest();

        verify(restTemplate).exchange(
                eq(RequestEntity.post(URI.create(url)).contentType(APPLICATION_JSON).body(requestBody)),
                eq(String.class)
        );
    }

}