package com.bkonecsni.logicgame.mapcreator.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static Properties getLoadedProperties(String propertyName) {
        Properties properties = new Properties();
        InputStream input;

        try {
            input = new FileInputStream("src/main/resources/properties/"+ propertyName +".properties");
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Property: " + propertyName + ".properties not exists in resources/properties!");
            System.exit(0);
        }

        return properties;
    }
}
