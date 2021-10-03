package com.util.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        InputStream reader = BaseTestCase.class.getClassLoader().getResourceAsStream("application.properties");
        properties = new Properties();

        try{
            properties.load(reader);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }

}
