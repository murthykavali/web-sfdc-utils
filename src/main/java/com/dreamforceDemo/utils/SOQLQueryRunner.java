package com.dreamforceDemo.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vivek on 29/7/15.
 */
public class SOQLQueryRunner {

    public static Map<String, Object> runQuery(String query, String sessionId, String queryUrl) throws IOException {
        String url = queryUrl + "?q=" + URLEncoder.encode(query, "UTF-8");
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("Authorization", "Bearer " + sessionId);
        return RESTApiCaller.get(url, headers);
    }

}
