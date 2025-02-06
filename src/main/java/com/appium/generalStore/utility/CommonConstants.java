package com.appium.generalStore.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonConstants {
	private static final Logger logger = LogManager.getLogger(CommonConstants.class);

	// Default values
	private static final String DEFAULT_ENV = "uat";
	private static final String DEFAULT_PLATFORM = "android";

	public static final String EXECUTION_ENV_NAME = getEnvironmentName();
	public static final String MOBILE_PLATFORM_NAME = getPlatformName();

	/**
	 * Fetch the environment name from system properties or use the default. Logs a
	 * warning if the environment is not explicitly provided.
	 *
	 * @return The execution environment name in lowercase.
	 */
	private static String getEnvironmentName() {
		String environmentName = System.getProperty("environment", DEFAULT_ENV).toLowerCase();

		if (environmentName.equals(DEFAULT_ENV)) {
			logger.warn("The default environment '{}' will be enabled for this run.", DEFAULT_ENV);
		} else {
			logger.warn("The Maven Profile is missing the environment configuration.");
		}

		return environmentName;
	}

	/**
	 * Fetch the platform name from system properties or use the default. Logs a
	 * warning if the platform is not explicitly provided.
	 *
	 * @return The platform name in lowercase.
	 */
	private static String getPlatformName() {
		String platformName = System.getProperty("platform", DEFAULT_PLATFORM).toLowerCase();

		if (platformName.equals(DEFAULT_PLATFORM)) {

			logger.warn("The default platform '{}' will be enabled for this run.", DEFAULT_PLATFORM);
		} else {
			logger.warn("The Maven Profile is missing the platform configuration.");
		}

		return platformName;
	}
}