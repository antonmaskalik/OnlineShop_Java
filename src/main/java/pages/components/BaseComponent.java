package pages.components;

import core.TestContext;
import helpers.WaitHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class BaseComponent {
    protected WebDriver driver;
    protected WaitHelper wait;

    public BaseComponent(TestContext context){
        this.driver = context.getDriver();
        this.wait = context.getWaitHelper();
        PageFactory.initElements(driver, this);
        log.debug("Initialized BaseComponent with driver: {}", driver);
    }
}
