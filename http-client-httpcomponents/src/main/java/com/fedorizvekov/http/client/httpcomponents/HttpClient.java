package com.fedorizvekov.http.client.httpcomponents;

import java.io.InputStream;
import java.util.Properties;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@Log4j2
public class HttpClient {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        }

        String serverUrl = properties.getProperty("server.url");
        String requestJson = properties.getProperty("request.json");

        HttpPost httpPost = new HttpPost(serverUrl);
        StringEntity entity = new StringEntity(requestJson, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {

            log.info("STATUS CODE: " + response.getStatusLine().getStatusCode());
            String responseBody = EntityUtils.toString(response.getEntity());
            log.info("RESPONSE BODY: " + responseBody);

        } catch (Exception exception) {
            log.error("Something went wrong, because: ", exception);
        }

    }

}
