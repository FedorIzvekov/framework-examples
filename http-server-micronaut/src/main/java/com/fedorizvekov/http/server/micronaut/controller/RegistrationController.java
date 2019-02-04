package com.fedorizvekov.http.server.micronaut.controller;

import javax.validation.Valid;
import com.fedorizvekov.http.server.micronaut.model.UserDto;
import com.fedorizvekov.http.server.micronaut.service.UserService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;


    @Post(value = "/users")
    @Status(HttpStatus.CREATED)
    public UserDto createUser(@Valid @Body UserDto userDto, HttpRequest request) {

        log.info("REQUEST POST endpoint {}", request.getUri().toString());

        return userService.saveUser(userDto);

    }

}
