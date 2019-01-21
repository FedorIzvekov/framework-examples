package com.fedorizvekov.http.client.httpcomponents.service.impl;

import java.io.IOException;
import com.fedorizvekov.http.client.httpcomponents.service.ClientService;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@Log4j2
public class ClientServiceImpl implements ClientService {

    @Override
    public void sendRequest(String url, String requestJson) {
        var httpPost = new HttpPost(url);
        var entity = new StringEntity(requestJson, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);

        try (var httpClient = HttpClients.createDefault();
             var response = httpClient.execute(httpPost)) {

            log.info("STATUS CODE: {}", response.getStatusLine().getStatusCode());
            var responseBody = EntityUtils.toString(response.getEntity());
            log.info("RESPONSE BODY: {}", responseBody);

        } catch (IOException exception) {
            log.error("HTTP request failed: ", exception);
        } catch (Exception exception) {
            log.error("Something went wrong, because: ", exception);
        }
    }

}
