package src.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import src.driverManager.DriverManager;
import src.pageObjects.mainPage.mainPage;

public class test {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new DriverManager().initDriver();
        driver.get("https://demoqa.com/frames");
    }

    @Test
    public void test() {
        mainPage mainpage = new mainPage(driver);
        mainpage.switchToIframe();
    }

    @AfterTest
    public void close() {
       driver.quit();
    }

}
