package com.appium.generalStore.util.driver;

import java.io.File;
import java.time.Duration;

import com.appium.generalStore.utility.PropertyReader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AndroidDriverServiceImpl implements MobileDriverService {
    private AppiumDriver androidDriver;

    @Override
    public void spinUpDriver(AppiumDriverLocalService appiumService) {
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid(PropertyReader.getDeviceConfig("android_device_name"))
                .setApp(new File(PropertyReader.getEnvironmentConfig("android_app_file_path")).getAbsolutePath())
                .setAppActivity(PropertyReader.getDeviceConfig("android_app_activity"))
                .setNoReset(Boolean.parseBoolean(PropertyReader.getDeviceConfig("android_no_reset")))
                .setFullReset(Boolean.parseBoolean(PropertyReader.getDeviceConfig("android_full_reset")))
                .autoGrantPermissions();

        androidDriver = new AndroidDriver(appiumService.getUrl(), options);
        androidDriver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Long.parseLong(PropertyReader.getAppiumConfig("appium_driver_timeout_in_seconds")))
        );
    }

    @Override
    public void closeDriver() {
        androidDriver.quit();
    }

    @Override
    public AppiumDriver getDriver() {
        return androidDriver;
    }
}