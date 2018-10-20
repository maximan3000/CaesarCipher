package com.grayen.encryption.cesar.keyword.hack.util;

import java.util.Map;

public class HackUtils {

    public static String getKeyByValue(Map<String, String> map, String value) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if ( value.equals(entry.getValue()) ) {
                return entry.getKey();
            }
        }
        return null;
    }

}
