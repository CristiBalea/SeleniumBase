package src.driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    public WebDriver driver = null;

    public WebDriver initDriver() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");

           return driver = new ChromeDriver(options);
    }
}
