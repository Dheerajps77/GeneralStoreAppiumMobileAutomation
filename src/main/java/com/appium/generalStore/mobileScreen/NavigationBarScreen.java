package com.appium.generalStore.mobileScreen;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;

import com.appium.generalStore.screenLocators.NavigationBarLocators;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class NavigationBarScreen {
	private final AppiumDriver driver;
    private final NavigationBarLocators locators;

    public NavigationBarScreen(AppiumDriver driver) {
        this.driver = driver;
        locators = new NavigationBarLocators();
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), locators);
    }

    public void tapOnLoginIcon() {
        locators.loginIcon.click();
    }

    public void tapOnSwipeIcon() {
        locators.swipeIcon.click();
    }
}
