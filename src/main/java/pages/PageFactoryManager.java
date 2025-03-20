package pages;

import core.TestContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageFactoryManager {
    private final TestContext context;

    public PageFactoryManager(TestContext context){
        this.context = context;
    }

    public HomePage getHomePage() {
        log.info("Initializing HomePage");
        return new HomePage(context);
    }

    public LoginPage getLoginPage() {
        log.info("Initializing LoginPage");
        return new LoginPage(context);
    }
}
