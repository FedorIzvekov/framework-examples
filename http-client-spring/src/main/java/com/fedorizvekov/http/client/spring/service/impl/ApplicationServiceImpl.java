package com.fedorizvekov.http.client.spring.service.impl;

import com.fedorizvekov.http.client.spring.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationContext context;

    public void shutdown() {
        SpringApplication.exit(context, () -> 0);
    }

}
