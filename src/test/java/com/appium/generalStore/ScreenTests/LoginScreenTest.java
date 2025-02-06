package com.appium.generalStore.ScreenTests;

import org.testng.annotations.Test;

import com.appium.generalStore.base.BaseClass;
import com.appium.generalStore.mobileScreen.LoginScreen;
import com.appium.generalStore.util.driver.MobileDriverHolder;

public class LoginScreenTest extends BaseClass {
	@Test
	public void testLogin() {
		//LoginScreen loginScreen = new LoginScreen(MobileDriverHolder.getDriver());
		//loginScreen.login("abc@gmail.com", "Password");
		//loginScreen.signUp("sign@gmail.com", "Sing");
		screens.getLoginScreen().login("abc@gmail.com", "Password");
		screens.getLoginScreen().signUp("sign@gmail.com", "Sing");
		// Add assertions here to verify the outcome
	}

}
