package core;

import configs.models.WebDriverConfigModel;
import core.webDriver.DriverManager;
import helpers.WaitHelper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

@Getter
@Slf4j
public class TestContext {
    private final WebDriver driver;
    private final WaitHelper waitHelper;

    public TestContext(WebDriverConfigModel config, ITestResult testResult) {
        DriverManager.createDriver(config);
        this.driver = DriverManager.getDriver();
        log.info("Driver created successfully for test: '{}'", testResult.getMethod().getMethodName());
        this.waitHelper = new WaitHelper(this.driver);
    }
}
