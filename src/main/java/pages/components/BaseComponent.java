package pages.components;

import core.TestContext;
import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseComponent {
    protected WebDriver driver;
    protected WaitHelper wait;

    public BaseComponent(TestContext context){
        this.driver = context.getDriver();
        this.wait = context.getWaitHelper();
        PageFactory.initElements(driver, this);
    }
}
