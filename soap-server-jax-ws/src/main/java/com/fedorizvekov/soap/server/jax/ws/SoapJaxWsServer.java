package com.fedorizvekov.soap.server.jax.ws;

import static java.lang.Thread.currentThread;

import java.io.InputStream;
import java.util.Properties;
import javax.xml.ws.Endpoint;
import com.fedorizvekov.soap.server.jax.ws.service.impl.RegistrationServiceImpl;

public class SoapJaxWsServer {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        try (InputStream input = currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        }

        String port = properties.getProperty("server.port");

        String address = "http://localhost:" + port + "/registration";
        Endpoint.publish(address, new RegistrationServiceImpl());

    }

}
