package src.dataProdiver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

@Log4j2
public class DriverManager {

    private static WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;

    public WebDriver initDriver() throws Exception {
        driverType = ConfigurationProperties.getDriverType();
        environmentType = ConfigurationProperties.getEnvironment();
        log.info("Running " + driverType.toString() + " " + environmentType.toString());

        return createDriver();
    }

    private static WebDriver createDriver() throws Exception {
        if (environmentType == EnvironmentType.LOCAL) {
            driver = createLocalDriver();
        } else if (environmentType == EnvironmentType.REMOTE) {
            driver = createRemoteDriver();
        }

        return driver;
    }

    private static WebDriver createRemoteDriver() throws Exception {
        if (driverType == DriverType.FIREFOX) {
            FirefoxOptions options = new FirefoxOptions();
            driver = new RemoteWebDriver(new URL(ConfigurationProperties.getRemoteUrl()), options);
        } else if (driverType == DriverType.CHROME) {
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
