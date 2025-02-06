import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.appium.example.constant.DriverConstants.ANDROID;

public class CommonConstants {
    private final static Logger logger = LogManager.getLogger();

    private final static String DEV = "dev";
    public static final String MOBILE_PLATFORM_NAME = getPlatformName();
    public static final String EXECUTION_ENV_NAME = getEnvironmentName();

    // Fetch environment name from system properties, default to 'dev' if missing
    private static String getEnvironmentName() {
        String environmentName = System.getProperty("environment", DEV);
        if (environmentName.equals(DEV)) {
            logger.warn("The Maven Profile is missing the environment configuration.");
            logger.warn("The default environment '{}' will be enabled for this run.", DEV);
        }
        return environmentName.toLowerCase();
    }

    // Fetch platform name from system properties, default to 'android' if missing
    private static String getPlatformName() {
        String platformName = System.getProperty("platform", ANDROID);
        if (platformName.equals(ANDROID)) {
            logger.warn("The Maven Profile is missing the platform configuration.");
            logger.warn("The default platform '{}' will be enabled for this run.", ANDROID);
        }
        return platformName.toLowerCase();
    }
}