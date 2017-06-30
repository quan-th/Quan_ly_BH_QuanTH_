/**
 * Copyright(C) Nov 28, 2016 Luvina,ValueProperties.java,Nov 28, 2016,LA-AM
 */
package com.example.demo.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author LA-AM
 *Ä�á»�c value tá»« file properties
 */
public class ValueProperties {
	static private Map<String, String> data = new HashMap<String, String>();

    /**
     * Constructor
     */
    static {
        Properties prop = new Properties();
        try {
            prop.load(MessageErrorProperties.class.getResourceAsStream(("/value.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration<String> en  = (Enumeration<String>)prop.propertyNames();
        while (en.hasMoreElements()) {
            String key = (String)en.nextElement();
            data.put(key, prop.getProperty(key));
        }
    }

    /**
     * getData from file properties
     * @param key key
     * @return String
     */
    static public String getValue(String key) {
        String string = "";
        if (data.containsKey(key)) {
            string = data.get(key);
        }
        return string;
    }
}
