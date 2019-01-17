package com.fedorizvekov.soap.server.jax.ws.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

    private String email;
    private String firstName;
    private String lastName;

}
