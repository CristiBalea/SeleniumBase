package src.driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.dataProdiver.ConfigurationProperties;
import src.dataProdiver.DriverType;
import src.dataProdiver.EnvironmentType;

import java.net.URL;

public class DriverManager {

    private static WebDriver driver;
    private static DriverType driverType;

    public WebDriver initDriver() throws Exception {
        driverType = ConfigurationProperties.getDriverType();
        return createDriver();
    }

    private static WebDriver createDriver() throws Exception {
        EnvironmentType environmentType = ConfigurationProperties.getEnvironment();
        if (environmentType == EnvironmentType.LOCAL) {
            driver = createLocalDriver();
        }

        if (environmentType == EnvironmentType.REMOTE) {
        driver = createRemoteDriver();
        }

        return driver;
    }

    private static WebDriver createRemoteDriver() throws Exception {
        if (driverType == DriverType.FIREFOX) {
            FirefoxOptions options = new FirefoxOptions();
            driver = new RemoteWebDriver(new URL(ConfigurationProperties.getRemoteUrl()), options);
        }

        if (driverType == DriverType.CHROME) {
            ChromeOptions options = new ChromeOptions();
            driver = new RemoteWebDriver(new URL(ConfigurationProperties.getRemoteUrl()), options);
        }

        return driver;
    }

    private static WebDriver createLocalDriver() {
        if (driverType == DriverType.FIREFOX) {
            System.setProperty("webdriver.gecko.driver", ConfigurationProperties.readProperty("geckoDriverPath"));
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        }

        if (driverType == DriverType.CHROME) {
            System.setProperty("webdriver.chrome.driver", ConfigurationProperties.readProperty("chromeDriverPath"));
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        }

        return driver;
    }

}
