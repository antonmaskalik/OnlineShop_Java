package tests.api;

import api.RestAssuredLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.PrintStream;

@ExtendWith(AllureJunit5.class)
public abstract class BaseTest {

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        String testName = testInfo.getDisplayName();
        ThreadContext.put("testName", testName);
        PrintStream restLogger = new RestAssuredLogger();
        RestAssured.filters(
                new RequestLoggingFilter(restLogger),
                new ResponseLoggingFilter(restLogger)
        );
    }

    @AfterEach
    public void tearDown() {
        ThreadContext.remove("testName");
    }
}