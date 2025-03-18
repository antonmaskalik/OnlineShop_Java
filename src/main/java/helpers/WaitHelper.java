package helpers;

import enums.WaitTimeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class WaitHelper {

    private final WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToBeVisible(WebElement element, WaitTimeout timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout.getSeconds()));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeInvisible(WebElement element, WaitTimeout timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout.getSeconds()));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element, WaitTimeout timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout.getSeconds()));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitForReadyStateComplete(WaitTimeout timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout.getSeconds()));
        try {
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitForCondition(Function<WebDriver, Boolean> condition, WaitTimeout timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout.getSeconds()));
        try {
            wait.until(condition);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

