package pages;

import core.TestContext;
import enums.WaitTimeout;
import helpers.EnumExtension;
import helpers.WaitHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.components.FooterComponent;
import pages.components.NavigationComponent;
import pages.enums.NavItem;

@Slf4j
public abstract class BasePage {
    protected WebDriver driver;
    protected WaitHelper wait;

    public FooterComponent footer;
    public NavigationComponent navBar;

    public BasePage(TestContext context) {
        this.driver = context.getDriver();
        this.wait = context.getWaitHelper();
        this.navBar = new NavigationComponent(context);
        PageFactory.initElements(driver, this);
        log.debug("Initialized BasePage with driver: {}", driver);
    }

    public boolean waitForUserIsLoggedIn(String username) {
        return wait.waitForCondition(driver -> navBar.getAllNavItems().get(navBar.getAllNavItems().size() - 1)
                .equals(EnumExtension.getStringValue(NavItem.WELCOME) + username), WaitTimeout.ELEMENT);
    }

    public boolean waitForUserIsLoggedOut() {
        return wait.waitForCondition(driver -> navBar.getAllNavItems().get(navBar.getAllNavItems().size() - 1)
                .equals(EnumExtension.getStringValue(NavItem.SIGN_UP)), WaitTimeout.ELEMENT);
    }
}
