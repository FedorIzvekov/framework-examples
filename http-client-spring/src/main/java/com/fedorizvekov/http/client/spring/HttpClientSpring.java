package com.fedorizvekov.http.client.spring;

import com.fedorizvekov.http.client.spring.service.ApplicationService;
import com.fedorizvekov.http.client.spring.service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HttpClientSpring {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(HttpClientSpring.class, args);
        ClientService clientService = context.getBean(ClientService.class);
        clientService.postRequest();

        ApplicationService applicationService = context.getBean(ApplicationService.class);
        applicationService.shutdown();

    }

}
