package com.fedorizvekov.soap.server.jax.ws.service.impl;

import javax.jws.WebService;
import com.fedorizvekov.soap.server.jax.ws.model.UserDto;
import com.fedorizvekov.soap.server.jax.ws.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebService(endpointInterface = "com.fedorizvekov.soap.server.jax.ws.service.RegistrationService")
public class RegistrationServiceImpl implements RegistrationService {

    Logger log = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Override
    public String registration(UserDto userDto) {

        log.info("REQUEST POST, endpoint /registration was called");

        return "REGISTRATION COMPLETED, " + userDto;
    }
}
