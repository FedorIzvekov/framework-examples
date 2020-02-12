package com.fedorizvekov.http.server.micronaut.service;

import java.util.List;
import com.fedorizvekov.http.server.micronaut.model.User;

public interface UserService {

    User saveUser(User user);

    List<User> findAll();

}
