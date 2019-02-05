package com.fedorizvekov.http.client.micronaut.service.impl;

import javax.inject.Singleton;
import com.fedorizvekov.http.client.micronaut.service.ClientService;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Singleton
public class ClientServiceImpl implements ClientService {

    private final @Client("host") HttpClient client;

    @Value("${request.path}")
    private String path;

    @Value("${request.json}")
    private String requestBody;

    public ClientServiceImpl(@Client("host") HttpClient httpClient) {
        this.client = httpClient;
    }


    public void postRequest() {

        var request = HttpRequest.POST(path, requestBody);
        var response = client.toBlocking().exchange(request, String.class);

        log.info("STATUS CODE: {}", response.getStatus());
        log.info("RESPONSE BODY: {}", response.getBody());

    }

}
