package com.appium.generalStore.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyReader {

    private static final Map<String, Properties> cachedProperties = new HashMap<>();

    // Generalized method to fetch property value from any properties file
    private static String getPropertyValue(String fileName, String propertyName) {
        Properties properties = loadProperties(fileName);
        return properties.getProperty(propertyName);
    }

    // Dynamically load properties based on file name and cache them for future use
    private static Properties loadProperties(String fileName) {
        if (!cachedProperties.containsKey(fileName)) {
            try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(fileName)) {
                if (inputStream == null) {
                    throw new RuntimeException("Property file not found: " + fileName);
                }
                Properties properties = new Properties();
                properties.load(inputStream);
                cachedProperties.put(fileName, properties);
            } catch (IOException ex) {
                throw new RuntimeException("Error reading properties file: " + fileName, ex);
            }
        }
        return cachedProperties.get(fileName);
    }

    // Appium-specific config
    public static String getAppiumConfig(String propertyName) {
        return getPropertyValue("appium.properties", propertyName);
    }

    // Device-specific config
    public static String getDeviceConfig(String propertyName) {
        return getPropertyValue("device.properties", propertyName);
    }

    // Environment-specific config
    public static String getEnvironmentConfig(String propertyName) {
        String returnString = null;
    	try {
        	String envFileName = "env/" + CommonConstants.EXECUTION_ENV_NAME + ".properties";
        	returnString= getPropertyValue(envFileName, propertyName);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return returnString;
    }
    

    // Reporter-specific config
    public static String getReporterConfig(String propertyName) {
        return getPropertyValue("test-reporter.properties", propertyName);
    }
    
    public static String getBrowserStackConfig(String propertyName) {
        return getPropertyValue("browserStack.properties", propertyName);
    }
}