package core.webDriver;

import configs.ConfigReader;
import enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    public static WebDriver createDriver(DriverType type) {
        return switch (type) {
            case CHROME -> createChromeDriver();
            case FIREFOX -> createFirefoxDriver();
            case EDGE -> createEdgeDriver();
            case REMOTE -> createRemoteWebDriver();
            default -> throw new IllegalArgumentException("Unsupported driver type: " + type);
        };
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver() {
        return new EdgeDriver();
    }

    private static WebDriver createRemoteWebDriver() {
        String hubUrl = ConfigReader.getDriverConfig().getRemoteHubUrl();
        ChromeOptions options = new ChromeOptions();
        try {
            return new RemoteWebDriver(new URL(hubUrl), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenoid URL", e);
        }
    }
}
