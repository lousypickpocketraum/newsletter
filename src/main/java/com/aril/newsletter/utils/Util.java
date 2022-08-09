package com.aril.newsletter.utils;

import java.util.Map;

public class Util {

    public static String insertParams (String content, Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            content = content.replace(changeParams(key), value.toString());
        }
        return content;
    }

    private static String changeParams(String param){
        return "#{" + param + "}";
    }
}
