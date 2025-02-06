package com.appium.generalStore.screenLocators;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SwipeLocators {
	@AndroidFindBy(accessibility = "You found me!!!")
	@iOSXCUITFindBy(accessibility = "find-me-logo")
	public WebElement findMeLogo;
}
