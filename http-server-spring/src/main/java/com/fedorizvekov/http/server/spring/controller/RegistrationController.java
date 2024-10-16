package com.fedorizvekov.http.server.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.fedorizvekov.http.server.spring.model.UserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class RegistrationController {

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public String registration(@Valid @RequestBody UserDto userDto, HttpServletRequest request) {

        log.info("REQUEST POST endpoint {}", request.getRequestURL().toString());

        return userDto.toString();

    }

}
