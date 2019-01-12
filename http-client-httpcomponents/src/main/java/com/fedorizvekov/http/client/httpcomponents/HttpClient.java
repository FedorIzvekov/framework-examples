package com.fedorizvekov.http.client.httpcomponents;

import java.util.Properties;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@Log4j2
public class HttpClient {

    public static void main(String[] args) throws Exception {

        var properties = new Properties();

        try (var input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        }

        var serverUrl = properties.getProperty("server.url");
        var requestJson = properties.getProperty("request.json");

        var httpPost = new HttpPost(serverUrl);
        var entity = new StringEntity(requestJson, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);

        try (var httpClient = HttpClients.createDefault();
             var response = httpClient.execute(httpPost)) {

            log.info("STATUS CODE: {}", response.getStatusLine().getStatusCode());
            var responseBody = EntityUtils.toString(response.getEntity());
            log.info("RESPONSE BODY: {}", responseBody);

        } catch (Exception exception) {
            log.error("Something went wrong, because: ", exception);
        }

    }

}
