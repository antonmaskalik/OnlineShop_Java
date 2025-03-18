package core;

import configs.models.ConfigModel;
import core.webDriver.DriverManager;
import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;

public class TestContext {
    private final WebDriver driver;
    private final WaitHelper waitHelper;

    public TestContext(ConfigModel config) {
        DriverManager.createDriver(config);
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
