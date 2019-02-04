package com.fedorizvekov.http.server.micronaut.service.impl;

import javax.inject.Singleton;
import com.fedorizvekov.http.server.micronaut.model.UserDto;
import com.fedorizvekov.http.server.micronaut.service.UserService;

@Singleton
public class UserServiceImpl implements UserService {

    @Override
    public UserDto saveUser(UserDto userDto) {
        return userDto;
    }

}
