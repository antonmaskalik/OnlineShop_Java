package core;

import configs.models.ConfigModel;
import core.webDriver.DriverManager;
import helpers.WaitHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

@Slf4j
public class TestContext {
    private final WebDriver driver;
    private final WaitHelper waitHelper;

    public TestContext(ConfigModel config, ITestResult testResult) {
        DriverManager.createDriver(config);
        this.driver = DriverManager.getDriver();
        log.info("Driver created successfully for test: '{}'", testResult.getMethod().getMethodName());
        this.waitHelper = new WaitHelper(this.driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WaitHelper getWaitHelper() {
        return waitHelper;
    }
}
