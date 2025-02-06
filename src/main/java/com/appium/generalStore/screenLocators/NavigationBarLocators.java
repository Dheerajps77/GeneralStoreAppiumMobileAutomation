package com.appium.generalStore.screenLocators;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class NavigationBarLocators {

	@AndroidFindBy(accessibility = "Login")
	@iOSXCUITFindBy(accessibility = "login-icon")
	public WebElement loginIcon;

	@AndroidFindBy(accessibility = "Swipe")
	@iOSXCUITFindBy(accessibility = "swipe-icon")
	public WebElement swipeIcon;

}
