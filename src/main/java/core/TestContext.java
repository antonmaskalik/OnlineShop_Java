package core;

import core.webDriver.DriverManager;
import enums.DriverType;
import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;

public class TestContext {
    private final WebDriver driver;
    private final WaitHelper waitHelper;

    public TestContext(DriverType driverType) {
        DriverManager.createDriver(driverType);
        this.driver = DriverManager.getDriver();
        this.waitHelper = new WaitHelper(this.driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WaitHelper getWaitHelper() {
        return waitHelper;
    }
}
