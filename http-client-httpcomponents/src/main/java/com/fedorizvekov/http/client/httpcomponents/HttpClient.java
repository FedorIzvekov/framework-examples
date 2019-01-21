package com.fedorizvekov.http.client.httpcomponents;

import com.fedorizvekov.http.client.httpcomponents.service.impl.ClientServiceImpl;
import com.fedorizvekov.http.client.httpcomponents.util.PropertiesUtil;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class HttpClient {

    public static void main(String[] args) throws Exception {

        var properties = PropertiesUtil.loadProperties("config.properties");
        var url = properties.getProperty("server.url");
        var requestJson = properties.getProperty("request.json");

        var clientService = new ClientServiceImpl();
        clientService.sendRequest(url, requestJson);
    }

}
