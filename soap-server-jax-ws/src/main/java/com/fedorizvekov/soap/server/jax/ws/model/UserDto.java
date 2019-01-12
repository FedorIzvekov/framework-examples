package com.fedorizvekov.soap.server.jax.ws.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String email;
    private String firstName;
    private String lastName;


    @Override
    public String toString() {
        return "User contain: ( " +
                "email = " + email +
                ", firstName = " + firstName +
                ", lastName = " + lastName + " )";
    }

}
