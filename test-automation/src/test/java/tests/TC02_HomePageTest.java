package tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.company.pages.HomePage;
import com.company.pages.LoginPage;
import com.framework.factory.PlaywrightFactory;
import com.framework.utils.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import com.framework.constants.AppConstants;

import static com.framework.factory.ConfigFactory.getConfig;

@Listeners(ChainTestListener.class)
public class TC02_HomePageTest extends BaseTest {
    protected static LoginPage loginPage;
    protected static HomePage  homePage;

    private TC02_HomePageTest() {
    }

    @Test(priority = 1)
    public void loginPageNavigationTest() {
        loginPage = new LoginPage();
        loginPage.navigate(getConfig().url());
        loginPage.getLoginPageTitle();
        loginPage.performLogin(getConfig().username(), getConfig().password());
        ScreenshotUtil.takeScreenshot();
    }

    @Test(priority = 2)
    public void homePageTitleTest() {
        homePage = new HomePage();
        Assert.assertEquals(homePage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE);
    }

    @Test(priority = 3)
    public void homePageURLTest() {
        homePage = new HomePage();
        Assert.assertEquals(homePage.getHomePageURL(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }

    @Test(priority = 4)
    public void searchTest() {
        homePage = new HomePage();
        homePage.navigateToPIM();
    }

}