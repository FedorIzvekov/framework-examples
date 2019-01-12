package com.fedorizvekov.http.client.spring;

import com.fedorizvekov.http.client.spring.service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpClientSpring {

    public static void main(String[] args) {

        var context = SpringApplication.run(HttpClientSpring.class, args);
        var clientService = context.getBean(ClientService.class);
        clientService.postRequest();

        SpringApplication.exit(context, () -> 0);

    }

}
