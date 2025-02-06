package com.appium.generalStore.util.driver;

import com.appium.generalStore.utility.PropertyReader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public interface MobileDriverService {

    /**
     * Starts the Appium server locally using configuration from the properties file.
     * @return an instance of {@link AppiumDriverLocalService}
     */
    default AppiumDriverLocalService startAppiumService() {
        String appiumServerIp = PropertyReader.getAppiumConfig("appium_server_ip");
        int appiumServerPort = Integer.parseInt(PropertyReader.getAppiumConfig("appium_server_port"));

        AppiumDriverLocalService appiumService = AppiumDriverLocalService
                .buildService(new AppiumServiceBuilder()
                        .withIPAddress(appiumServerIp)
                        .usingPort(appiumServerPort)
                        .withArgument(() -> "--use-plugins", "gestures")); // Add gestures plugin
        try {
            appiumService.start();
            if (!appiumService.isRunning()) {
                throw new IllegalStateException("Failed to start the Appium service.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error starting Appium service: " + e.getMessage(), e);
        }
        return appiumService;
    }

    /**
     * Stops the running Appium server.
     *
     * @param appiumService the instance of {@link AppiumDriverLocalService} to stop
     */
    default void stopAppiumService(AppiumDriverLocalService appiumService) {
        if (appiumService != null && appiumService.isRunning()) {
            appiumService.stop();
        } else {
            System.out.println("Appium service is not running or already stopped.");
        }
    }

    /**
     * Spins up a new Appium driver instance.
     *
     * @param appiumService the instance of {@link AppiumDriverLocalService} to use
     */
    void spinUpDriver(AppiumDriverLocalService appiumService);

    /**
     * Closes the active Appium driver instance.
     */
    void closeDriver();

    /**
     * Retrieves the current Appium driver instance.
     *
     * @return the current {@link AppiumDriver}
     */
    AppiumDriver getDriver();
}