package tests;

import io.qameta.allure.Step;
import org.testng.annotations.Test;
import dataProdiver.ConfigurationProperties;
import pageObjects.MainPage;
import template.BaseTestTemplate;

public class PlayTest extends BaseTestTemplate {

    MainPage mainPage;

    @Test(description = "test")
    public void test() {
        mainPage = new MainPage(getDriver());

        String url = ConfigurationProperties.getTestUrl();

        getDriver().get(url);

        mainPage.waitForHeaderLogo(10);
        mainPage.isHeaderLogoVisible();
    }

}
