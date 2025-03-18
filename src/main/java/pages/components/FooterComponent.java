package pages.components;

import core.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FooterComponent extends BaseComponent{
    @FindBy(id = "footc")
    private WebElement footerBanner;

    @FindBy(css = "footer>p")
    private WebElement copyrightText;

    public FooterComponent(TestContext context){
        super(context);
    }

    public boolean isFooterDisplayed(){
        return footerBanner.isDisplayed() && copyrightText.isDisplayed();
    }
}
