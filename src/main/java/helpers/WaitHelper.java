package helpers;

import enums.WaitTimeout;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

@Slf4j
public class WaitHelper {

    private final WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToBeVisible(WebElement element, WaitTimeout timeout) {
        log.info("Waiting for element to be visible: {}", element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout.getSeconds()));
        wait.until(ExpectedConditions.visibilityOf(element));
        log.info("Element is now visible: {}", element);
    }

    public void waitForElementToBeInvisible(WebElement element, WaitTimeout timeout) {
        log.info("Waiting for element to be invisible: {}", element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout.getSeconds()));
        wait.until(ExpectedConditions.invisibilityOf(element));
        log.info("Element is invisible: {}", element);
    }

    public void waitForElementToBeClickable(WebElement element, WaitTimeout timeout) {
        log.info("Waiting for element to be clickable: {}", element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout.getSeconds()));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        log.info("Element is clickable: {}", element);
    }

    public boolean waitForReadyStateComplete(WaitTimeout timeout) {
        log.info("Waiting for document ready state to be complete.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout.getSeconds()));
        try {
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
            log.info("Document ready state is complete.");
            return true;
        } catch (Exception e) {
            log.warn("Document ready state is not complete within the timeout period.");
            return false;
        }
    }

    public boolean waitForCondition(Function<WebDriver, Boolean> condition, WaitTimeout timeout) {
        log.info("Waiting for custom condition to be met.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout.getSeconds()));
        try {
            wait.until(condition);
            log.info("Custom condition is met.");
            return true;
        } catch (Exception e) {
            log.warn("Custom condition was not met within the timeout period.");
            return false;
        }
    }
}

