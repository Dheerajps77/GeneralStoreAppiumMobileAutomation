package com.appium.generalStore.util.driver;

import com.appium.generalStore.utility.PropertyReader;

public class MobileDriverFactory {
    public MobileDriverService getDriverService() {
        // Fetch the mobile platform name from the property file
        String mobilePlatformName = PropertyReader.getEnvironmentConfig("mobile_platform_name").toLowerCase();

        switch (mobilePlatformName) {
            case "android":
                return new AndroidDriverServiceImpl();
            case "ios":
                return new IosDriverServiceImpl();
            case "browserstack":
                return new BrowserStackDriverServiceImpl();
            default:
                throw new IllegalArgumentException(
                        "Invalid platform: " + mobilePlatformName + ". Please set platform as 'android' or 'ios' in the properties file.");
        }
    }
}