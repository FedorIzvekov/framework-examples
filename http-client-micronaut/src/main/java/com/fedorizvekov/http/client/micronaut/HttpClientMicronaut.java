package com.fedorizvekov.http.client.micronaut;

import com.fedorizvekov.http.client.micronaut.service.ClientService;
import io.micronaut.runtime.Micronaut;

public class HttpClientMicronaut {

    public static void main(String[] args) {

        var context = Micronaut.run(HttpClientMicronaut.class);
        var clientService = context.getBean(ClientService.class);
        clientService.postRequest();
        context.close();

    }

}
