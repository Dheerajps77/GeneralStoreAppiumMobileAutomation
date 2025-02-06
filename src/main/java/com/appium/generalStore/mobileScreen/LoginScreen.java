package com.appium.generalStore.mobileScreen;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;

import com.appium.generalStore.screenLocators.LoginLocators;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginScreen {

	private final AppiumDriver driver;
	private final LoginLocators locators;

	public LoginScreen(AppiumDriver driver) {
		this.driver = driver;
		locators = new LoginLocators();
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), locators);
	}

	public void login(String email, String password) {
		locators.emailInput.sendKeys(email);
		locators.passwordInput.sendKeys(password);
		locators.loginButton.click();
	}

	public void signUp(String email, String password) {
		locators.emailInput.sendKeys(email);
		locators.passwordInput.sendKeys(password);
		locators.signUpButton.click();
	}
}
