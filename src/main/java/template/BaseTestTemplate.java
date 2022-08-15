package template;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import dataProdiver.DriverManager;
import org.testng.annotations.BeforeTest;


public class BaseTestTemplate  {

    protected WebDriver driver;

    @BeforeTest(alwaysRun = true)
    protected void initiateDriver() throws Exception {
        driver = new DriverManager().initDriver();
    }

    @AfterTest(alwaysRun = true)
    protected void close() {
        driver.quit();
    }

    protected WebDriver getDriver() {
        return driver;
    }

}
