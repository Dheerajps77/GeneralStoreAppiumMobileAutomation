package com.appium.generalStore.util.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;

import com.appium.generalStore.utility.PropertyReader;
import com.appium.generalStore.utility.TestContextProvider;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BrowserStackDriverServiceImpl implements MobileDriverService {
    private AppiumDriver driver;

    @Override
    public void spinUpDriver(AppiumDriverLocalService appiumService) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Fetch BrowserStack credentials and app URL
        String username = PropertyReader.getBrowserStackConfig("browserstack_username");
        String accessKey = PropertyReader.getBrowserStackConfig("browserstack_access_key");
        String appUrl = PropertyReader.getBrowserStackConfig("browserstack_app_url");

        capabilities.setCapability("browserstack.user", username);
        capabilities.setCapability("browserstack.key", accessKey);
        capabilities.setCapability("app", appUrl);

        // Fetch TestNG parameters dynamically
        ITestContext context = TestContextProvider.getContext();
        String platformName = context.getCurrentXmlTest().getParameter("platformName");
        String device = context.getCurrentXmlTest().getParameter("device");
        String osVersion = context.getCurrentXmlTest().getParameter("os_version");

        // Set capabilities for the platform, device, and OS version
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("device", device);
        capabilities.setCapability("os_version", osVersion);

        // Additional capabilities for better reporting and configuration
        capabilities.setCapability("project", PropertyReader.getEnvironmentConfig("browserstack_project_name"));
        capabilities.setCapability("build", PropertyReader.getEnvironmentConfig("browserstack_build_name"));
        capabilities.setCapability("name", context.getName()); // Use test name for better reporting

        try {
            // Initialize the driver with the BrowserStack hub URL
            driver = new AppiumDriver(new URL(PropertyReader.getBrowserStackConfig("browserstack_hub_url")), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid BrowserStack hub URL: " + e.getMessage(), e);
        }
    }
    
    /*
    @Override
    public void spinUpDriver(AppiumDriverLocalService appiumService) {
        // Create DesiredCapabilities for BrowserStack
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        // Fetch common BrowserStack credentials from properties
        String username = PropertyReader.getBrowserStackConfig("browserstack_username");
        String accessKey = PropertyReader.getBrowserStackConfig("browserstack_access_key");
        String appUrl = PropertyReader.getBrowserStackConfig("browserstack_url"); // The uploaded app URL on BrowserStack
        
        // Common capabilities
        capabilities.setCapability("browserstack.user", PropertyReader.getBrowserStackConfig("browserstack_username"));
        capabilities.setCapability("browserstack.key", PropertyReader.getBrowserStackConfig("browserstack_access_key"));
        capabilities.setCapability("app", PropertyReader.getBrowserStackConfig("browserstack_url"));
        capabilities.setCapability("project", PropertyReader.getEnvironmentConfig("browserstack_project_name"));
        capabilities.setCapability("build", PropertyReader.getEnvironmentConfig("browserstack_build_name"));
        capabilities.setCapability("name", PropertyReader.getEnvironmentConfig("browserstack_test_name"));

        // Fetch platform/device-specific capabilities from the properties
        String platformName = PropertyReader.getDeviceConfig("platform_name").toLowerCase();
        if ("android".equals(platformName)) {
            capabilities.setCapability("platformName", PropertyReader.getBrowserStackConfig("platform_name"));
            capabilities.setCapability("device", PropertyReader.getBrowserStackConfig("android_device_name"));
            capabilities.setCapability("os_version", PropertyReader.getBrowserStackConfig("android_os_version"));
        } else if ("ios".equals(platformName)) {
            capabilities.setCapability("platformName", PropertyReader.getBrowserStackConfig("platform_name"));
            capabilities.setCapability("device", PropertyReader.getBrowserStackConfig("ios_device_name"));
            capabilities.setCapability("os_version", PropertyReader.getBrowserStackConfig("ios_os_version"));
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platformName);
        }

        // Initialize the driver with BrowserStack hub URL
        try {
            driver = new AppiumDriver(new URL(PropertyReader.getBrowserStackConfig("browserstack_url")), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid BrowserStack hub URL: " + e.getMessage(), e);
        }
    }
    */

    @Override
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public AppiumDriver getDriver() {
        return driver;
    }
}