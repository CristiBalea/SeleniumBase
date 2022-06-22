package src.template;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.driverManager.DriverManager;

import java.time.Duration;

@Log4j2
public class BasePageTemplate extends DriverManager {

    private static final int defaultTimeout = 30;
    public WebDriver driver;

    public BasePageTemplate(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Default wait used
     * If seconds is less than 0 then value is set to default 30.
     *
     * @param driver WebDriver driver
     * @param seconds time to wait in seconds
     * @return new WebDriverWait instance
     */
    public WebDriverWait pageWait(WebDriver driver, int seconds) {
        seconds = seconds > 0 ? seconds : defaultTimeout;
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    /**
     * Default wait used
     * If seconds is less than 0 then value is set to default 30.
     *
     * @param seconds time to wait in seconds
     * @return new WebDriverWait instance
     */
    public WebDriverWait pageWait(int seconds) {
        return pageWait(driver, seconds);
    }

    /**
     * Waits for element to be visible
     *
     * @param seconds Time to wait in seconds
     * @param locator element locator
     * @return WebElement if found, null otherwise
     */
    protected WebElement waitForElementVisible(By locator, int seconds) {
        return pageWait(seconds).
                until(new ExpectedCondition<>() {

                    @Override
                    public WebElement apply(WebDriver driver) {
                        log.info("looking for: " + locator.toString());
                        try {
                            WebElement element = driver.findElement(locator);
                            if (element == null || !element.isDisplayed()
                                    || element.getSize().getWidth() <= 0
                                    || element.getSize().getHeight() <= 0) {
                                return null;
                            }
                            return element;
                        } catch (WebDriverException e) {
                            return null;
                        }
                    }

                    @Override
                    public String toString() {
                        return "visibility of element located by: " + locator.toString();
                    }
                });
    }

    /**
     * Waits for element to be visible
     *
     * @param locator Element locator
     * @return WebElement if found, null otherwise
     */
    protected WebElement waitForElementVisible(By locator) {
        return waitForElementVisible(locator, defaultTimeout);
    }

    /**
     * Waits for element to be visible
     *
     * @param seconds time to wait
     * @param element WebElement
     * @return WebElement if found, null otherwise
     */
    protected WebElement waitForElementVisible(WebElement element, int seconds) {
        return pageWait(seconds).
                until(new ExpectedCondition<>() {

                    @Override
                    public WebElement apply(WebDriver driver) {
                        log.info("looking for: " + element.toString());
                        try {
                            if (element == null
                                    || !element.isDisplayed()
                                    || element.getSize().getWidth() <= 0
                                    || element.getSize().getHeight() <= 0) {
                                return null;
                            }
                            return element;
                        } catch (WebDriverException e) {
                            return null;
                        }
                    }

                    @Override
                    public String toString() {
                        return "visibility of element located by: " + element.toString();
                    }
                });
    }

    /**
     * Waits for element to be visible
     *
     * @param element WebElement
     * @return WebElement if found, null otherwise
     */
    protected WebElement waitForElementVisible(WebElement element) {
        return waitForElementVisible(element, defaultTimeout);
    }

    /**
     * Checks if the element is visible
     *
     * @param locator Element locator
     * @param seconds Time to wait in seconds
     * @return True if the element is found, false otherwise
     */
    protected boolean isElementVisible(By locator, int seconds) {
        return pageWait(seconds).
                until(new ExpectedCondition<>() {

                    @Override
                    public Boolean apply(WebDriver driver) {
                        log.info("looking for visibility of element located by: " + locator.toString());
                        WebElement element = driver.findElement(locator);
                        try {
                            if (element == null
                                    || !element.isDisplayed()
                                    || element.getSize().getWidth() <= 0
                                    || element.getSize().getHeight() <= 0) {
                                return false;
                            }
                            return true;
                        } catch (WebDriverException e) {
                            return null;
                        }
                    }

                    @Override
                    public String toString() {
                        return "visibility of element located by: " + locator.toString();
                    }
                });
    }

    /**
     * Checks if the element is visible
     *
     * @param locator Element locator
     * @return True if the element is found, false otherwise
     */
    protected boolean isElementVisible(By locator) {
        return isElementVisible(locator, defaultTimeout);
    }

    /**
     * Waits for element to become clickable
     *
     * @param seconds Time to wait in seconds
     * @param locator Element locator
     * @return WebElement if the element becomes clickable, null otherwise
     */
    protected  WebElement waitForElementClickable(By locator, int seconds) {
        return pageWait(seconds)
                .until(new ExpectedCondition<>() {
            public WebElement apply(WebDriver driver) {
                log.info("looking for element to be clickable: " + locator.toString());
                WebElement element = driver.findElement(locator);
                try {
                    return element != null && element.isEnabled() ? element : null;
                } catch (StaleElementReferenceException ex) {
                    return null;
                }
            }

            public String toString() {
                return "element to be clickable: " + locator;
            }
        });
    }

    /**
     * Waits for element to become clickable
     *
     * @param locator Element locator
     * @return WebElement if the element becomes clickable, null otherwise
     */
    protected WebElement waitForElementClickable(By locator) {
        return waitForElementClickable(locator, defaultTimeout);
    }

    /**
     * Waits for iframe to be visible then switches to it
     *
     * @param locator iframe locator
     */
    protected void waitForFrameAndSwitchToIt(By locator) {
         pageWait(defaultTimeout)
                 .until(new ExpectedCondition<>() {
            public WebDriver apply(WebDriver driver) {
                log.info("looking for frame to be located by: " + locator.toString());
                try {
                    WebElement element = driver.findElement(locator);
                    return driver.switchTo().frame(element);
                } catch (NoSuchFrameException var3) {
                    return null;
                }
            }

            public String toString() {
                return "frame to be available: " + locator.toString();
            }
        });
    }

    /**
     * Waits for iframe to be visible then switches to it
     *
     * @param index iframe index
     */
    protected void waitForFrameAndSwitchToIt(int index) {
        pageWait(defaultTimeout)
                .until(new ExpectedCondition<>() {
                    public WebDriver apply(WebDriver driver) {
                        log.info("looking for frame to be located by index: " + index);
                        try {
                            return driver.switchTo().frame(index);
                        } catch (NoSuchFrameException var3) {
                            return null;
                        }
                    }

                    public String toString() {
                        return String.format("frame at index %s to be available: ", index);
                    }
                });
    }

    /**
     * Waits for iframe to be visible then switches to it
     *
     * @param frameNameOrId iframe name or id as string
     */
    protected void waitForFrameAndSwitchToIt(String frameNameOrId) {
        pageWait(defaultTimeout)
                .until(new ExpectedCondition<>() {
                    public WebDriver apply(WebDriver driver) {
                        log.info("looking for frame to be located by name: " + frameNameOrId);
                        try {
                            return driver.switchTo().frame(frameNameOrId);
                        } catch (NoSuchFrameException var3) {
                            return null;
                        }
                    }

                    public String toString() {
                        return "frame with name at to be available: " + frameNameOrId;
                    }
                });
    }
}
