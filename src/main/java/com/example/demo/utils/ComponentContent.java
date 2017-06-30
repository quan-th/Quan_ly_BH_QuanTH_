/**
 * Copyright(C) Nov 24, 2016 Luvina,ComponentContent.java,Nov 24, 2016,LA-AM
 */
package com.example.demo.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author LA-AM
 *
 */
public class ComponentContent {
	static private Map<String, String> data = new HashMap<String, String>();

    /**
     * Constructor
     */
    static {
        Properties prop = new Properties();
        try {
            prop.load(MessageErrorProperties.class.getResourceAsStream(("/component_content.properties")));
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
    static public String getData(String key) {
        String string = "";
        if (data.containsKey(key)) {
            string = data.get(key);
        }
        return string;
    }
}
