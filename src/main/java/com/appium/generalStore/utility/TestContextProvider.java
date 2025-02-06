package com.appium.generalStore.utility;

import org.testng.ITestContext;

public class TestContextProvider {

	private static ThreadLocal<ITestContext> contextHolder = new ThreadLocal<>();

	public static void setContext(ITestContext context) {
		contextHolder.set(context);
	}

	public static ITestContext getContext() {
		return contextHolder.get();
	}
}
