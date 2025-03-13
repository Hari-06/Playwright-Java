package tests;

import base.BaseTest;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.company.pages.HomePage;
import com.company.pages.LoginPage;
import com.framework.constants.AppConstants;
import com.framework.factory.PlaywrightFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.framework.factory.ConfigFactory.getConfig;

@Listeners(ChainTestListener.class)
public class TC01LoginPageTest extends BaseTest {
    protected HomePage homePage;
    protected LoginPage loginPage;

    private TC01LoginPageTest() {
    }

    @Test(priority = 1)
    public void loginPageNavigationTest() {

        loginPage = new LoginPage(PlaywrightFactory.getPage());
        loginPage.navigate(getConfig().url());
        Assert.assertEquals(loginPage.getLoginPageTitle(), AppConstants.LOGIN_PAGE_TITLE);
        ChainTestListener.embed(PlaywrightFactory.takeScreenshot(),"image/png");
    }

    @Test(priority = 2)
    public void forgotPwdLinkExistTest() {
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
        ChainTestListener.embed(PlaywrightFactory.takeScreenshot(),"image/png");
    }

    @Test(priority = 3)
    public void appLoginTest() {
        Assert.assertTrue(loginPage.performLogin(getConfig().username(), getConfig().password()));
        ChainTestListener.embed(PlaywrightFactory.takeScreenshot(),"image/png");
    }
}
