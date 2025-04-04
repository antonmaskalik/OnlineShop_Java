package core.webDriver;

import configs.ConfigReader;
import enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class DriverFactory {

    public static WebDriver createDriver(BrowserType type, boolean isRemote) {
        if (isRemote) {
            return createRemoteWebDriver(type);
        }
        return createLocalWebDriver(type);
    }

    private static WebDriver createLocalWebDriver(BrowserType type) {
        log.info("Creating local WebDriver for {} browser", type);
        return switch (type) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver(new ChromeOptions());
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver(new FirefoxOptions());
            }
            case EDGE -> {
                WebDriverManager.edgedriver().create();
                yield new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Unsupported browser type: " + type);
        };
    }

    private static WebDriver createRemoteWebDriver(BrowserType type) {
        String hubUrl = ConfigReader.getDriverConfig().getRemoteHubUrl();
        List<String> options = List.of("--headless");
        log.info("Creating RemoteWebDriver for {} browser using {} hub URL", type, hubUrl);

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
            log.error("Invalid Selenoid URL", e);
            throw new RuntimeException("Invalid Selenoid URL", e);
        }
    }
}
