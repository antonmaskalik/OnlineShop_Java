package pages;

import core.TestContext;
import enums.WaitTimeout;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class LoginPage extends BasePage {
    @FindBy(id = "loginusername")
    private WebElement userNameField;

    @FindBy(id = "loginpassword")
    private WebElement passwordField;

    @FindBy(css = "#logInModal [class='btn btn-primary']")
    private WebElement logInButton;

    public LoginPage(TestContext context){
        super(context);
        log.debug("Initialized LoginPage.");
    }

    public void enterUsername(String username) {
        log.info("Entering username: {}", username);
        wait.waitForElementToBeVisible(userNameField, WaitTimeout.ELEMENT);
        userNameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        log.info("Entering password.");
        wait.waitForElementToBeVisible(passwordField, WaitTimeout.ELEMENT);
        passwordField.sendKeys(password);
    }

    public void clickLogInButton() {
        log.info("Clicking login button.");
        logInButton.click();
    }
}
