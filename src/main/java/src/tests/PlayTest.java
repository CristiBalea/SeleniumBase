package src.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import src.dataProdiver.ConfigurationProperties;
import src.template.BaseTestTemplate;

public class PlayTest extends BaseTestTemplate {

    WebDriver driver;

    @Test
    public void test() throws Exception {
        driver = initiateDriver();

        String url = ConfigurationProperties.getTestUrl();
        driver.get(url);
    }

}
