package core;

import configs.models.ConfigModel;

public class TestContextManager {
    private static final ThreadLocal<TestContext> testContextThreadLocal = new ThreadLocal<>();

    public static void createContext(ConfigModel config) {
        testContextThreadLocal.set(new TestContext(config));
    }

    public static TestContext getContext() {
        return testContextThreadLocal.get();
    }

    public static void clearContext() {
        testContextThreadLocal.remove();
    }
}
