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
    protected static LoginPage loginPage;
    protected static HomePage  homePage;

    private TC02_HomePageTest() {
    }

    @Test(priority = 1)
    public void loginPageNavigationTest() {
        loginPage = new LoginPage(PlaywrightManager.getPage());
        loginPage.navigate(getConfig().url());
        loginPage.getLoginPageTitle();
        HomePage home = loginPage.performLogin(getConfig().username(), getConfig().password());
        Assert.assertEquals(home.getHomePageTitle(), FrameworkConstants.HOME_PAGE_TITLE);
        Assert.assertEquals(home.getHomePageURL(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        home.navigateTo("PIM");
        ScreenshotUtil.takeScreenshot();
    }
    @Test(priority = 2)
    public void loginPageNavigationTest2() {
        loginPage = new LoginPage(PlaywrightManager.getPage());
        loginPage.navigate(getConfig().url());
        loginPage.getLoginPageTitle();
        HomePage home = loginPage.performLogin(getConfig().username(), getConfig().password());
        Assert.assertEquals(home.getHomePageTitle(), FrameworkConstants.HOME_PAGE_TITLE);
        Assert.assertEquals(home.getHomePageURL(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        home.navigateTo("PIM");
        ScreenshotUtil.takeScreenshot();
    }

}