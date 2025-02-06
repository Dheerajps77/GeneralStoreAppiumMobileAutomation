package com.appium.generalStore.util.driver;

import java.io.File;
import java.time.Duration;

import com.appium.generalStore.utility.PropertyReader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class IosDriverServiceImpl implements MobileDriverService {
    private AppiumDriver iosDriver;

    @Override
    public void spinUpDriver(AppiumDriverLocalService appiumService) {
        XCUITestOptions options = new XCUITestOptions()
                .setDeviceName(PropertyReader.getDeviceConfig("ios_device_name"))
                .setPlatformVersion(PropertyReader.getDeviceConfig("ios_platform_version"))
                .setApp(new File(PropertyReader.getEnvironmentConfig("ios_app_file_path")).getAbsolutePath())
                .setNoReset(Boolean.parseBoolean(PropertyReader.getDeviceConfig("ios_no_reset")))
                .setFullReset(Boolean.parseBoolean(PropertyReader.getDeviceConfig("ios_full_reset")))
                .autoAcceptAlerts();

        iosDriver = new IOSDriver(appiumService.getUrl(), options);
        iosDriver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Long.parseLong(PropertyReader.getAppiumConfig("appium_driver_timeout_in_seconds")))
        );
    }

    @Override
    public void closeDriver() {
        iosDriver.quit();
    }

    @Override
    public AppiumDriver getDriver() {
        return iosDriver;
    }
}