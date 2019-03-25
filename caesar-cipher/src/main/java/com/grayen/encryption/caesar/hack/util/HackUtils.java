package com.grayen.encryption.caesar.hack.util;

import java.util.Map;

/**
 * Common methods for general purposes
 */
public class HackUtils {

    public static String getKeyByValue(Map<String, String> map, String value) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if ( value.equals(entry.getValue()) ) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static Boolean checkIfKeySameWithValue(String key, Map map) {
        return key.equals(map.get(key));
    }

    public static Boolean containsKeyWithValue(String key, String value, Map map) {
        return (map.containsKey(key) && map.get(key).equals(value));
    }

}
