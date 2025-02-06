package com.appium.generalStore.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.appium.generalStore.mobileScreen.ScreenProvider;
import com.appium.generalStore.util.driver.MobileDriverFactory;
import com.appium.generalStore.util.driver.MobileDriverHolder;
import com.appium.generalStore.util.driver.MobileDriverService;
import com.appium.generalStore.utility.PropertyReader;
import com.appium.generalStore.utility.TestContextProvider;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass {

	private static final Logger logger = LogManager.getLogger(BaseClass.class);
	private AppiumDriverLocalService appiumService;
	private MobileDriverService driverService;
	protected ScreenProvider screens;

	@BeforeClass(alwaysRun = true)
	public void setUp(ITestContext context) {
		try {
			TestContextProvider.setContext(context);
			// Start Appium service
			appiumService = new MobileDriverFactory().getDriverService().startAppiumService();

			// Get the appropriate driver service based on platform
			driverService = new MobileDriverFactory().getDriverService();

			// Start Android Emulator
			startEmulator();

			// Spin up the driver (Android or iOS based on configuration)
			driverService.spinUpDriver(appiumService);

			// Set the Appium driver in the MobileDriverHolder
			AppiumDriver driver = driverService.getDriver();
			MobileDriverHolder.setDriver(driver);

			screens = new ScreenProvider(driver);

			// Set a default implicit wait timeout
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
			logger.info("Appium driver set up successfully.");

		} catch (Exception e) {
			logger.error("Failed to set up Appium driver: ", e);
			throw new RuntimeException("Failed to set up Appium driver", e);
		}
	}

	/**
	 * Method to start Android Emulator.
	 */
	public void startEmulator() {
		try {
			String emulatorName = PropertyReader.getDeviceConfig("emulatorName"); /* "Pixel_6_Pro" */ // Replace with your emulator's AVD name
			if (emulatorName == null || emulatorName.isEmpty()) {
				logger.info("Emulator name not found in properties file");
				throw new RuntimeException("Emulator name not found in properties file");
			}

			logger.info("Starting Android Emulator: " + emulatorName);
			System.out.println("Starting Android Emulator: " + emulatorName);
			ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "emulator -avd " + emulatorName);
			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();

			// Print emulator output for debugging
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				// Ensure the emulator has booted by checking for specific boot message
				if (line.contains("boot completed")) {
					break;
				}
			}

			// Wait for emulator to boot completely (extend if necessary)
			Thread.sleep(20000); // Wait time can be adjusted as per the emulator speed
			System.out.println("Emulator started successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		try {
			// Close the Appium driver and stop Appium service
			if (MobileDriverHolder.getDriver() != null) {
				MobileDriverHolder.getDriver().quit();
			}
			if (appiumService != null) {
				new MobileDriverFactory().getDriverService().stopAppiumService(appiumService);
			}
			logger.info("Appium driver and service stopped successfully.");
		} catch (Exception e) {
			logger.error("Error during driver or service shutdown: ", e);
		}
	}
}
