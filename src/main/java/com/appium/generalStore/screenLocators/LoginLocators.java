package com.appium.generalStore.screenLocators;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginLocators {

	@AndroidFindBy(accessibility = "input-email")
	@iOSXCUITFindBy(accessibility = "email-input")
	public WebElement emailInput;

	@AndroidFindBy(accessibility = "input-password")
	@iOSXCUITFindBy(accessibility = "password-input")
	public WebElement passwordInput;

	@AndroidFindBy(accessibility = "button-LOGIN")
	@iOSXCUITFindBy(accessibility = "login-button")
	public WebElement loginButton;

	@AndroidFindBy(accessibility = "button-SIGN UP")
	@iOSXCUITFindBy(accessibility = "signup-button")
	public WebElement signUpButton;
}
