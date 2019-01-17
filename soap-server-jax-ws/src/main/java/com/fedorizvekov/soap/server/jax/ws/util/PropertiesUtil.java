package com.fedorizvekov.soap.server.jax.ws.util;

import static java.lang.Thread.currentThread;
import static java.util.Optional.ofNullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    public static Properties loadProperties(String filePath) throws IOException {
        var properties = new Properties();

        try (var input = ofNullable(currentThread().getContextClassLoader().getResourceAsStream(filePath))
                .orElseThrow(() -> new FileNotFoundException("'" + filePath + "' not found in the classpath"))
        ) {
            properties.load(input);
        }

        return properties;
    }

}
