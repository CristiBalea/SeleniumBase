package tests;

import TestUtils.TestAllureListener;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import dataProdiver.ConfigurationProperties;
import pageObjects.MainPage;
import template.BaseTestTemplate;

@Listeners({TestAllureListener.class})
public class PlayTest extends BaseTestTemplate {

    MainPage mainPage;

    @Step("Url check")
    @Attachment
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
