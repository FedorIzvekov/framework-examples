package com.fedorizvekov.soap.server.jax.ws;

import static java.lang.Thread.currentThread;

import java.util.Properties;
import javax.xml.ws.Endpoint;
import com.fedorizvekov.soap.server.jax.ws.service.impl.RegistrationServiceImpl;

public class SoapJaxWsServer {

    public static void main(String[] args) throws Exception {

        var properties = new Properties();

        try (var input = currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        }

        var port = properties.getProperty("server.port");
        var address = "http://localhost:" + port + "/registration";
        Endpoint.publish(address, new RegistrationServiceImpl());

    }

}
