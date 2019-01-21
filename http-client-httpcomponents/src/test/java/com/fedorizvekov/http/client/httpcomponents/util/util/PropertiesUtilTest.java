package com.fedorizvekov.http.client.httpcomponents.util.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.fedorizvekov.http.client.httpcomponents.util.PropertiesUtil;
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
                () -> assertThat(properties.getProperty("server.url")).isEqualTo("http://localhost:8080/users"),
                () -> assertThat(properties.getProperty("request.json")).isEqualTo("{\"email\": \"client@email.com\", \"firstName\": \"clientFirstName\", \"lastName\": \"clientLastName\"}")
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
