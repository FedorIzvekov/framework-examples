package com.fedorizvekov.http.server.micronaut.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import javax.inject.Inject;
import com.fedorizvekov.http.server.micronaut.model.User;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@MicronautTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RegistrationControllerTest {

    @Inject
    @Client("/users")
    HttpClient client;


    @DisplayName("Should return CREATED and saved user")
    @Order(1)
    @Test
    void shouldReturn_CREATED_andSavedUser() {
        var userDto = User.builder().email("test@email.com").firstName("first_name").lastName("last_name").build();
        var request = HttpRequest.POST("http://localhost:8086/users", userDto);

        var result = client.toBlocking().exchange(request, Argument.of(User.class));

        assertAll(
                () -> assertThat(result.getStatus().toString()).isEqualTo(HttpStatus.CREATED.toString()),
                () -> assertThat(result.body().toString()).isEqualTo("User(id=1, email=test@email.com, firstName=first_name, lastName=last_name)")
        );
    }


    @DisplayName("Should return OK and all users")
    @Order(2)
    @Test
    void shouldReturn_OK_andAllUsers() {
        var request = HttpRequest.GET("http://localhost:8086/users");

        var result = client.toBlocking().exchange(request, Argument.of(List.class));

        assertAll(
                () -> assertThat(result.getStatus().toString()).isEqualTo(HttpStatus.OK.toString()),
                () -> assertThat(result.body().toString()).isEqualTo("[{id=1, email=test@email.com, lastName=last_name, firstName=first_name}]")
        );
    }

}
