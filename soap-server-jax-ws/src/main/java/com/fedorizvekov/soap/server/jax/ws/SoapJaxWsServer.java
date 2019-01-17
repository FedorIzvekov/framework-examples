package com.fedorizvekov.soap.server.jax.ws;

import javax.xml.ws.Endpoint;
import com.fedorizvekov.soap.server.jax.ws.service.impl.RegistrationServiceImpl;
import com.fedorizvekov.soap.server.jax.ws.util.PropertiesUtil;

public class SoapJaxWsServer {

    public static void main(String[] args) throws Exception {
        var properties = PropertiesUtil.loadProperties("config.properties");
        var port = properties.getProperty("server.port");
        var address = "http://localhost:" + port + "/registration";

        Endpoint.publish(address, new RegistrationServiceImpl());
    }

}
