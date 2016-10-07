package com.fedorizvekov.http.client.spring.service.impl;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.net.URI;
import com.fedorizvekov.http.client.spring.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final RestTemplate restTemplate;

    @Value("${server.url}")
    private String url;

    @Value("${request.json}")
    private String requestBody;


    public void postRequest() {

        try {

            ResponseEntity<String> response = restTemplate.exchange(
                    RequestEntity.post(URI.create(url))
                            .contentType(APPLICATION_JSON)
                            .body(requestBody),
                    String.class
            );

            log.info("STATUS CODE: " + response.getStatusCode());
            log.info("RESPONSE BODY: " + response.getBody());

        } catch (Exception exception) {
            log.error("Something went wrong, because: ", exception);
        }

    }

}
