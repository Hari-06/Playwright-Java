package tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.framework.factory.PlaywrightFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import com.framework.constants.AppConstants;

import static com.framework.factory.ConfigFactory.getConfig;

@Listeners(ChainTestListener.class)
public class TC02_HomePageTest extends BaseTest {

    @Test
    public void homePageTitleTest() {
        Assert.assertEquals(homePage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE);
        ChainTestListener.embed(PlaywrightFactory.takeScreenshot(),"image/png");
    }

    @Test
    public void homePageURLTest() {
        Assert.assertEquals(homePage.getHomePageURL(), getConfig().url());
        ChainTestListener.embed(PlaywrightFactory.takeScreenshot(),"image/png");
    }

    @DataProvider
    public Object[][] getProductData() {
        return new Object[][] {
                { "Macbook" },
                { "iMac" },
                { "Samsung" }
        };
    }

    @Test(dataProvider = "getProductData")
    public void searchTest(String productName) throws InterruptedException {
        Thread.sleep(5000);
        String actualSearchHeader = homePage.doSearch(productName);
        Assert.assertEquals(actualSearchHeader, "Search - " + productName);
        ChainTestListener.embed(PlaywrightFactory.takeScreenshot(),"image/png");
    }

}