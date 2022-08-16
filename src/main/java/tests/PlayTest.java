package tests;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import dataProdiver.ConfigurationProperties;
import pageObjects.MainPage;
import template.BaseTestTemplate;

public class PlayTest extends BaseTestTemplate {

    MainPage mainPage;

    @Test(description = "Test")
    public void test(ITestContext context) {
        mainPage = new MainPage(getDriver());

        String url = ConfigurationProperties.getTestUrl();

        getDriver().get(url);

        mainPage.waitForHeaderLogo(10);
        mainPage.isHeaderLogoVisible();

        Assert.assertEquals(getDriver().getTitle(), "QAT");
    }

}
