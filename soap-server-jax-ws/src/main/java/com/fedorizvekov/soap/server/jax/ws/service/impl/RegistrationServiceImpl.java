package com.fedorizvekov.soap.server.jax.ws.service.impl;

import javax.jws.WebService;
import com.fedorizvekov.soap.server.jax.ws.model.UserDto;
import com.fedorizvekov.soap.server.jax.ws.service.RegistrationService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebService(endpointInterface = "com.fedorizvekov.soap.jax.ws.server.service.RegistrationService")
public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public String registration(UserDto userDto) {

        log.info("REQUEST POST, endpoint /registration was called");

        return "REGISTRATION COMPLETED, " + userDto;
    }
}
