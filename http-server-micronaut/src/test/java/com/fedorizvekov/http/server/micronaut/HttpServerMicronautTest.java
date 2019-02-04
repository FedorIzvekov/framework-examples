package com.fedorizvekov.http.server.micronaut;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@MicronautTest
class HttpServerMicronautTest {

    @Inject
    private EmbeddedApplication<?> application;


    @DisplayName("Should is running")
    @Test
    void shouldIsRunning() {
        assertThat(application.isRunning()).isTrue();
    }

}
