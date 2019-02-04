package com.fedorizvekov.http.server.micronaut.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import javax.inject.Inject;
import com.fedorizvekov.http.server.micronaut.model.UserDto;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@MicronautTest
class RegistrationControllerTest {

    @Inject
    @Client("/users")
    HttpClient client;


    @DisplayName("Should return CREATED and saved user")
    @Test
    void shouldReturn_CREATED_andSavedUser() {
        var userDto = UserDto.builder().email("test@email.com").firstName("first_name").lastName("last_name").build();
        var request = HttpRequest.POST("http://localhost:8086/users", userDto);

        var result = client.toBlocking().exchange(request, Argument.of(UserDto.class));

        assertAll(
                () -> assertThat(result.getStatus().toString()).isEqualTo(HttpStatus.CREATED.toString()),
                () -> assertThat(result.body().toString()).isEqualTo("UserDto(email=test@email.com, firstName=first_name, lastName=last_name)")
        );
    }

}
