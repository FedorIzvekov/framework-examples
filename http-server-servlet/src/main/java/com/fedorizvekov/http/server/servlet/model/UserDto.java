package com.fedorizvekov.http.server.servlet.model;

public class UserDto {

    private String email;
    private String firstName;
    private String lastName;


    public void setEmail(String email) {
        this.email = email;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return new StringBuilder().append("email: ")
                .append(email)
                .append(", firstName: ")
                .append(firstName)
                .append(", lastName: ")
                .append(lastName)
                .toString();
    }

}
