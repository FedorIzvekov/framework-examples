package com.fedorizvekov.http.server.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.fedorizvekov.http.server.spring.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RegistrationController {

    @PostMapping("/registration")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String registration(@Valid @RequestBody UserDto userDto, HttpServletRequest request) {

        log.info("REQUEST POST endpoint {}", request.getRequestURL().toString());

        log.info("Request contain user: {}", userDto.toString());

        return "REGISTRATION COMPLETED, " + userDto;

    }

}
