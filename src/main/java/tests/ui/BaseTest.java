package tests.ui;

import configs.ConfigReader;
import configs.models.WebDriverConfigModel;
import core.TestContext;
import core.TestContextManager;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import core.webDriver.DriverManager;
import pages.PageFactoryManager;

@Listeners({AllureTestNg.class})
public abstract class BaseTest {
    private static final ThreadLocal<TestContext> contextThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<PageFactoryManager> pageFactoryThreadLocal = new ThreadLocal<>();

    @BeforeMethod
    public void setUp(ITestResult testResult) {
        ThreadContext.put("testName", testResult.getMethod().getMethodName());

        WebDriverConfigModel config = ConfigReader.getDriverConfig();
        TestContextManager.createContext(config, testResult);
        TestContext context = TestContextManager.getContext();

        contextThreadLocal.set(context);
        pageFactoryThreadLocal.set(new PageFactoryManager(context));

        context.getDriver().manage().window().maximize();
        context.getDriver().navigate().to(config.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {
        ThreadContext.remove("testName");
        TestContextManager.clearContext();
        DriverManager.quitDriver();

        contextThreadLocal.remove();
        pageFactoryThreadLocal.remove();
    }

    protected PageFactoryManager getPageFactory() {
        return pageFactoryThreadLocal.get();
    }
}
