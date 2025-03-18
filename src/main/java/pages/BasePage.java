package pages;

import core.TestContext;
import enums.WaitTimeout;
import helpers.EnumExtension;
import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.components.FooterComponent;
import pages.components.NavigationComponent;
import pages.enums.NavItem;

public abstract class BasePage {
    protected  WebDriver driver;
    protected  WaitHelper wait;

    public FooterComponent footer;
    public NavigationComponent navBar;

    public BasePage(TestContext context){
        this.driver = context.getDriver();
        this.wait = context.getWaitHelper();
        this.navBar = new NavigationComponent(context);
        PageFactory.initElements(driver, this);
    }

    public boolean waitForUserIsLoggedIn(String username) {
        return wait.waitForCondition(driver -> navBar.getAllNavItems().getLast()
                .equals(EnumExtension.getStringValue(NavItem.WELCOME) + username), WaitTimeout.ELEMENT);
    }

    public boolean waitForUserIsLoggedOut() {
        return wait.waitForCondition(driver -> navBar.getAllNavItems().getLast()
                .equals(EnumExtension.getStringValue(NavItem.SIGN_UP)), WaitTimeout.ELEMENT);
    }
}
