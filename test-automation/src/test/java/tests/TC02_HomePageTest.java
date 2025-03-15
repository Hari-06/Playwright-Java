package tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.company.pages.HomePage;
import com.company.pages.LoginPage;
import com.framework.factory.PlaywrightFactoryold;
import com.framework.factory.PlaywrightManager;
import com.framework.utils.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import com.framework.constants.FrameworkConstants;

import static com.framework.factory.ConfigFactory.getConfig;

@Listeners(ChainTestListener.class)
public class TC02_HomePageTest extends BaseTest {

    private TC02_HomePageTest() {
    }

    @Test(priority = 1, groups = {"Sanity"})
    public void loginPageNavigationTest() {
        LoginPage loginPage;
        HomePage  homePage;
        loginPage = new LoginPage(getPage());
        loginPage.navigate(getConfig().url());
        loginPage.getLoginPageTitle();
        homePage = loginPage.performLogin(getConfig().username(), getConfig().password());
        Assert.assertEquals(homePage.getHomePageTitle(), FrameworkConstants.HOME_PAGE_TITLE);
        Assert.assertEquals(homePage.getHomePageURL(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        homePage.navigateTo("PIM");
        ScreenshotUtil.takeScreenshot();
    }
    @Test(priority = 2,groups = {"Sanity"})
    public void loginPageNavigationTest2() {
        LoginPage loginPage;
        HomePage  homePage;
        loginPage = new LoginPage(getPage());
        loginPage.navigate(getConfig().url());
        loginPage.getLoginPageTitle();
        homePage = loginPage.performLogin(getConfig().username(), getConfig().password());
        Assert.assertEquals(homePage.getHomePageTitle(), FrameworkConstants.HOME_PAGE_TITLE);
        Assert.assertEquals(homePage.getHomePageURL(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        homePage.navigateTo("PIM");
        ScreenshotUtil.takeScreenshot();
    }

}