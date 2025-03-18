package tests.ui;

import configs.ConfigReader;
import configs.models.ConfigModel;
import core.TestContext;
import core.TestContextManager;
import org.testng.annotations.*;
import core.webDriver.DriverManager;
import core.listeners.ScreenshotListener;
import pages.PageFactoryManager;

@Listeners(ScreenshotListener.class)
public abstract class BaseTest {
    private static final ThreadLocal<TestContext> contextThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<PageFactoryManager> pageFactoryThreadLocal = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        ConfigModel config = ConfigReader.getDriverConfig();
        TestContextManager.createContext(config);
        TestContext context = TestContextManager.getContext();

        contextThreadLocal.set(context);
        pageFactoryThreadLocal.set(new PageFactoryManager(context));

        context.getDriver().navigate().to(config.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {
        TestContextManager.clearContext();
        DriverManager.quitDriver();

        contextThreadLocal.remove();
        pageFactoryThreadLocal.remove();
    }

    protected TestContext getContext() {
        return contextThreadLocal.get();
    }

    protected PageFactoryManager getPageFactory() {
        return pageFactoryThreadLocal.get();
    }
}
