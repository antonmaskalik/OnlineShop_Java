package core.webDriver;

import configs.ConfigReader;
import enums.BrowserType;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DriverFactory {
    public static WebDriver createDriver(BrowserType type, boolean isRemote) {
        if (isRemote) {
            return createRemoteWebDriver(type);
        }
        return createLocalWebDriver(type);
    }

    private static WebDriver createLocalWebDriver(BrowserType type) {
        return switch (type) {
            case CHROME -> new ChromeDriver(new ChromeOptions());
            case FIREFOX -> new FirefoxDriver(new FirefoxOptions());
            case EDGE -> new EdgeDriver();
            default -> throw new IllegalArgumentException("Unsupported browser type: " + type);
        };
    }

    private static WebDriver createRemoteWebDriver(BrowserType type) {
        String hubUrl = ConfigReader.getDriverConfig().getRemoteHubUrl();
        List<String> options = List.of("--headless");

        MutableCapabilities capabilities = switch (type) {
            case CHROME -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                options.forEach(chromeOptions::addArguments);
                yield chromeOptions;
            }
            case FIREFOX -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                options.forEach(firefoxOptions::addArguments);
                yield firefoxOptions;
            }
            case EDGE -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                options.forEach(edgeOptions::addArguments);
                yield edgeOptions;
            }
            default -> throw new IllegalArgumentException("Unsupported browser type for remote execution: " + type);
        };

        try {
            return new RemoteWebDriver(new URL(hubUrl), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenoid URL", e);
        }
    }
}
