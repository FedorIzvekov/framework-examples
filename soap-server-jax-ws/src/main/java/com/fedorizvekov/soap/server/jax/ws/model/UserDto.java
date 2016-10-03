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
        return new StringBuilder()
                .append("User contain: ( ")
                .append("email = ")
                .append(email)
                .append(", firstName = ")
                .append(firstName)
                .append(", lastName = ")
                .append(lastName)
                .append(" )")
                .toString();
    }

}
