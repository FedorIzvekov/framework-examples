package com.fedorizvekov.http.server.micronaut.service;

import com.fedorizvekov.http.server.micronaut.model.UserDto;

public interface UserService {

    UserDto saveUser(UserDto userDto);

}
