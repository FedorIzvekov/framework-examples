package com.fedorizvekov.http.server.servlet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private String email;
    private String firstName;
    private String lastName;


    @JsonProperty("name")
    public void setName(String name) {
        this.firstName = name;
    }


    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
