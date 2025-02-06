package com.appium.generalStore.mobileScreen;

import io.appium.java_client.AppiumDriver;

public class ScreenProvider {
    private final AppiumDriver driver;

    // Constructor to initialize AppiumDriver
    public ScreenProvider(AppiumDriver driver) {
        this.driver = driver;
    }

    // Factory methods to get screen objects
    public LoginScreen getLoginScreen() {
        return new LoginScreen(driver);
    }

    public NavigationBarScreen getNavigationBarScreen() {
        return new NavigationBarScreen(driver);
    }

    public SwipeScreen getSwipeScreen() {
        return new SwipeScreen(driver);
    }
}