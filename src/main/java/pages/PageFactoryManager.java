package pages;

import core.TestContext;

public class PageFactoryManager {
    private final TestContext context;

    public PageFactoryManager(TestContext context){
        this.context = context;
    }

    public HomePage getHomePage() {
        return new HomePage(context);
    }

    public LoginPage getLoginPage() {
        return new LoginPage(context);
    }
}
