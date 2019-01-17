package com.fedorizvekov.soap.server.jax.ws.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PropertiesUtilTest {


    @DisplayName("Should load properties")
    @Test
    void shouldLoadProperties() throws IOException {
        var properties = PropertiesUtil.loadProperties("config.properties");

        assertAll(
                () -> assertThat(properties).isNotNull(),
                () -> assertThat(properties.getProperty("server.port")).isEqualTo("8082")
        );
    }


    @DisplayName("Should throw FileNotFoundException")
    @Test
    void shouldThrowFileNotFoundException() {
        assertThatThrownBy(() -> PropertiesUtil.loadProperties("invalid.properties"))
                .isInstanceOf(FileNotFoundException.class)
                .hasMessageContaining("'invalid.properties' not found in the classpath");
    }

}