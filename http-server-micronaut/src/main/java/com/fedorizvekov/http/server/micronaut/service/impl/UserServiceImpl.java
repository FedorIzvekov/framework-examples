package com.fedorizvekov.http.server.micronaut.service.impl;

import java.util.List;
import javax.inject.Singleton;
import com.fedorizvekov.http.server.micronaut.model.User;
import com.fedorizvekov.http.server.micronaut.repository.UserRepository;
import com.fedorizvekov.http.server.micronaut.service.UserService;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;


    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }


    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

}
