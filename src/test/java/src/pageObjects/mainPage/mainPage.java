package src.pageObjects.mainPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import src.template.BasePageTemplate;

public class mainPage extends BasePageTemplate {

    private static String DEFAULT_PAGE_CSS = " ";
    public static By HEADER_LOGO = By.cssSelector("#app a img[src*='Toolsqa']");
    public static By INACTIVE_BUTTON = By.cssSelector("#app #enableAfter");
    public static By VISIBLE_AFTER = By.cssSelector("#app #visibleAfter");

    // iframes
    public static By IFRAME_ONE = By.cssSelector("#frame1");

    public mainPage(WebDriver driver) {
        super(driver);
    }

    public void waitForHeaderLogo(int seconds) {
        waitForElementClickable(INACTIVE_BUTTON, seconds);
    }

    public boolean isHeaderLogoVisible() {
        return isElementVisible(VISIBLE_AFTER, 10);
    }

    public void switchToIframe() {
        waitForFrameAndSwitchToIt(IFRAME_ONE);
    }

}
