package core;

import enums.DriverType;

public class TestContextManager {
    private static final ThreadLocal<TestContext> testContextThreadLocal = new ThreadLocal<>();

    public static void createContext(DriverType driverType) {
        testContextThreadLocal.set(new TestContext(driverType));
    }

    public static TestContext getContext() {
        return testContextThreadLocal.get();
    }

    public static void clearContext() {
        testContextThreadLocal.remove();
    }
}
