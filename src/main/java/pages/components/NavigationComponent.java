package pages.components;

import core.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.enums.NavItem;
import helpers.EnumExtension;

import java.util.List;
import java.util.Optional;

public class NavigationComponent extends BaseComponent {
    private static final By navigatesLocator = By.cssSelector("[class^='nav-link']:not([style*='none'])");

    @FindBy(id = "navbarExample")
    private WebElement navigationBar;

    public NavigationComponent(TestContext context){
        super(context);
    }

    public void ClickNavigationItem(NavItem navItem) {
        String navItemName = EnumExtension.getStringValue(navItem);
        Optional<WebElement> navItemElement = getNavItems().stream()
                .filter(item -> item.getText().trim().equals(navItemName))
                .findFirst();
        navItemElement.ifPresent(WebElement::click);
    }

    public List<String> getAllNavItems() {
        return getNavItems().stream()
                .map(WebElement::getText).toList();
    }

    private List<WebElement> getNavItems() {
        return navigationBar.findElements(navigatesLocator);
    }
}
