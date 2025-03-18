package core.webDriver;

import configs.models.ConfigModel;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void createDriver(ConfigModel config) {
        if (driverThreadLocal.get() == null) {
            driverThreadLocal.set(DriverFactory.createDriver(config.getBrowser(), config.IsRemoteRun()));
        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
