package com.fedorizvekov.soap.server.jax.ws.model;

public class UserDto {

    private String email;
    private String firstName;
    private String lastName;


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
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
