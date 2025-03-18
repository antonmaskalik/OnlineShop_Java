package pages;

import core.TestContext;
import enums.WaitTimeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "loginusername")
    private WebElement userNameField;

    @FindBy(id = "loginpassword")
    private WebElement passwordField;

    @FindBy(css = "#logInModal [class='btn btn-primary']")
    private WebElement logInButton;

    public LoginPage(TestContext context){
        super(context);
    }

    public void enterUsername(String username) {
        wait.waitForElementToBeVisible(userNameField, WaitTimeout.ELEMENT);
        userNameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.waitForElementToBeVisible(passwordField, WaitTimeout.ELEMENT);
        passwordField.sendKeys(password);
    }

    public void clickLogInButton() {
        logInButton.click();
    }
}
