package com.dreamforceDemo.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created by vivek on 29/7/15.
 */
public class RESTApiCaller {

    public static Map<String, Object> get(String url, Map<String, Object> headers) throws IOException {
        Client client = Client.create();

        WebResource webResource = client.resource(url);

        for (Map.Entry<String, Object> header : headers.entrySet()) {
            webResource.header(header.getKey(), header.getValue());
        }

        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String output = response.getEntity(String.class);

        return convertStringToMap(output);
    }

    private static Map<String, Object> convertStringToMap(String output) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> responseMap = mapper.readValue(output, Map.class);
        return responseMap;
    }


    public static Map<String, Object> post(String url, Map<String, Object> headers, String body) throws IOException {
        Client client = Client.create();

        WebResource webResource = client
                .resource(url);

        String input = body;

        ClientResponse response = webResource.type("application/json")
                .post(ClientResponse.class, input);

        for (Map.Entry<String, Object> header : headers.entrySet()) {
            webResource.header(header.getKey(), header.getValue());
        }


        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        String output = response.getEntity(String.class);
        return convertStringToMap(output);
    }
}
