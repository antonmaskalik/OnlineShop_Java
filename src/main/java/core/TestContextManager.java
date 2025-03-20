package core;

import configs.models.ConfigModel;
import org.testng.ITestResult;

public class TestContextManager {
    private static final ThreadLocal<TestContext> testContextThreadLocal = new ThreadLocal<>();

    public static void createContext(ConfigModel config, ITestResult testResult) {
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
