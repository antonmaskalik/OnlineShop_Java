package core;

import configs.models.WebDriverConfigModel;
import org.testng.ITestResult;

public class TestContextManager {
    private static final ThreadLocal<TestContext> testContextThreadLocal = new ThreadLocal<>();

    public static void createContext(WebDriverConfigModel config, ITestResult testResult) {
        TestContext context = new TestContext(config, testResult);
        testContextThreadLocal.set(context);
    }

    public static TestContext getContext() {
        return testContextThreadLocal.get();
    }

    public static void clearContext() {
        testContextThreadLocal.remove();
    }
}
