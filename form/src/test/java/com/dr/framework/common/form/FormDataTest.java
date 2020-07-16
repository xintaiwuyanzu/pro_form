package com.dr.framework.common.form;

import org.apache.commons.collections4.map.MultiKeyMap;

public class FormDataTest {
    public static void main(String[] args) {
        MultiKeyMap<String, String> keyMap = new MultiKeyMap();
        keyMap.put("a", "b", "c");
        System.out.println(keyMap);
        System.out.println(keyMap.containsKey("a", "b"));
        System.out.println(keyMap.get("a"));
    }
}
