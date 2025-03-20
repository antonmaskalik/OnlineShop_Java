package core.listeners;

import core.TestContextManager;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        log.info("Starting test: {}", result.getName());
        log.info("Test ID: {}", result.getTestClass().getName());
        log.info("Test Method: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test succeeded: {}", result.getName());
        attachLogs(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test failed: {}", result.getName());
        attachLogs(result);

        String screenshotsDirectory = "target/screenshots/";
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String testName = result.getName();
        String fileName = testName + "_" + timestamp + ".png";

        File screenshot = ((TakesScreenshot) TestContextManager.getContext().getDriver()).getScreenshotAs(OutputType.FILE);

        try {
            Files.createDirectories(Paths.get(screenshotsDirectory));
            Files.copy(screenshot.toPath(), Paths.get(screenshotsDirectory + fileName));

            try (InputStream is = Files.newInputStream(Paths.get(screenshotsDirectory + fileName))) {
                Allure.getLifecycle().addAttachment(testName, "image/png", "png", IOUtils.toByteArray(is));
            }
        } catch (IOException e) {
            log.error("Error during screenshot attachment", e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test skipped: {}", result.getName());
        attachLogs(result);
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Starting test suite: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Finished test suite: {}", context.getName());
    }

    private void attachLogs(ITestResult result) {
        String testMethodName = result.getMethod().getMethodName();
        String logFilePath = "logs/" + testMethodName + ".log";
        try (InputStream is = Files.newInputStream(Paths.get(logFilePath))) {
            Allure.getLifecycle().addAttachment(testMethodName + " Logs", "text/plain", ".log", IOUtils.toByteArray(is));
        } catch (IOException e) {
            log.error("Failed to read log file.", e);
        }
    }
}
