package src.template;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import src.dataProdiver.DriverManager;


public class BaseTestTemplate  {

    public WebDriver driver;

    public WebDriver initiateDriver() throws Exception {
        driver = new DriverManager().initDriver();

        return driver;
    }

    @AfterTest
    public void close() {
        driver.quit();
    }

}
