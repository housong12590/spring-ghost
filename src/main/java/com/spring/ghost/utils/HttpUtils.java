package com.spring.ghost.utils;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class HttpUtils {

    public static <T> T get(String url, Map<String, Object> params, Class<T> cls) {
        RestTemplate template = new RestTemplate();
        return template.getForObject(url, cls);
    }
}
