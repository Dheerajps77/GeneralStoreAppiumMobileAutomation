package com.appium.generalStore.mobileScreen;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;

import com.appium.generalStore.screenLocators.SwipeLocators;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwipeScreen {

    private final AppiumDriver driver;
    private final SwipeLocators locators;

    public SwipeScreen(AppiumDriver driver) {
        this.driver = driver;
        locators = new SwipeLocators();
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), locators);
    }

    public void scrollToFindMeLogo() {
        // Use driver or methods from BaseScreen to scroll to the locator
        locators.findMeLogo.click();
    }
}
