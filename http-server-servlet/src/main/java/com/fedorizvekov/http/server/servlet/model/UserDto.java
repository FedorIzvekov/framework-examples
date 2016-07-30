package com.fedorizvekov.http.server.servlet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private String email;
    private String firstName;
    private String lastName;


    public void setEmail(String email) {
        this.email = email;
    }


    @JsonProperty("name")
    public void setName(String name) {
        this.firstName = name;
    }


    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


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
