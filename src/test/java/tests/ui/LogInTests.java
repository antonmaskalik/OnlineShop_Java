package tests.ui;

import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.enums.NavItem;

public class LogInTests extends BaseTest {
    private static final String username = "onlineShopTest";
    private static final String password = "123456";

    @Test
    @Description("Verify user log in")
    public void LogInTest() {
        HomePage homePage = getPageFactory().getHomePage();
        LoginPage loginPage = getPageFactory().getLoginPage();

        homePage.navBar.ClickNavigationItem(NavItem.LOG_IN);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogInButton();

        Assert.assertTrue(loginPage.waitForUserIsLoggedIn(username), "User is not logged in with username: " + username);
    }

    @Test
    @Description("Verify user log out")
    public void LogOutTest() {
        HomePage homePage = getPageFactory().getHomePage();
        LoginPage loginPage = getPageFactory().getLoginPage();

        homePage.navBar.ClickNavigationItem(NavItem.LOG_IN);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogInButton();
        loginPage.waitForUserIsLoggedIn(username);
        homePage.navBar.ClickNavigationItem(NavItem.LOG_OUT);

        Assert.assertTrue(loginPage.waitForUserIsLoggedOut(), "User could not logged out  ");
    }
}
