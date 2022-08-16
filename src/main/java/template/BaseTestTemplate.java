package template;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import dataProdiver.DriverManager;
import org.testng.annotations.BeforeTest;


public class BaseTestTemplate  {

    public WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void initiateDriver() throws Exception {
        driver = new DriverManager().initDriver();
    }

    @AfterTest(alwaysRun = true)
    public void close() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
