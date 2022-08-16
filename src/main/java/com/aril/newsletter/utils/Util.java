package com.aril.newsletter.utils;

import com.aril.newsletter.payloads.response.MailAddressResponse;
import com.aril.newsletter.repositories.IMailEntityRepository;

import java.util.HashMap;
import java.util.Map;

public class Util {
    private final IMailEntityRepository mailEntityRepository;

    public Util(IMailEntityRepository mailEntityRepository) {
        this.mailEntityRepository = mailEntityRepository;
    }

    public static String insertParams (String content, Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            content = content.replace(changeParams(key), value.toString());
        }
        return content;
    }

    public static String createParams(MailAddressResponse mailAddressResponse){
        HashMap<String, String> map = new HashMap<>();
        map.put("name",mailAddressResponse.getName());
        map.put("edas",mailAddressResponse.getEdas());
        return map.toString();
    }

    private static String changeParams(String param){
        return "#{" + param + "}";
    }
}
