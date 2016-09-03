package com.fedorizvekov.soap.server.jax.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.fedorizvekov.soap.server.jax.ws.model.UserDto;

@WebService
public interface RegistrationService {

    @WebMethod
    String registration(
            @WebParam(
                    name = "registrationRequest",
                    targetNamespace = "http://model.ws.jax.server.soap.fedorizvekov.com/"
            ) UserDto userDto
    );

}
