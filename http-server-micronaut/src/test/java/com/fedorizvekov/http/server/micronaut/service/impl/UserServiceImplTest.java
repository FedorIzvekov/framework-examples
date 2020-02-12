package com.fedorizvekov.http.server.micronaut.service.impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import com.fedorizvekov.http.server.micronaut.model.User;
import com.fedorizvekov.http.server.micronaut.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository repository;


    @DisplayName("Should invoke repository save")
    @Test
    public void ShouldInvoke_repositorySave() {
        var user = User.builder().build();
        userService.saveUser(user);
        verify(repository).save(eq(user));
    }


    @DisplayName("Should invoke repository findAll")
    @Test
    public void ShouldInvoke_repositoryFindAll() {
        userService.findAll();
        verify(repository).findAll();
    }

}
