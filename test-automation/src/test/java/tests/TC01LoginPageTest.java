package tests;

import base.BaseTest;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.company.pages.LoginPage;
import com.framework.constants.FrameworkConstants;
import com.framework.factory.PlaywrightFactoryold;
import com.framework.factory.PlaywrightManager;
import com.framework.utils.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.framework.factory.ConfigFactory.getConfig;

@Listeners(ChainTestListener.class)
public class TC01LoginPageTest extends BaseTest {
    protected static LoginPage loginPage;

    private TC01LoginPageTest() {
    }


    @Test(priority = 1)
    public void loginTest1() {
        loginPage = new LoginPage(PlaywrightManager.getPage());
        loginPage.navigate(getConfig().url());
        Assert.assertEquals(loginPage.getLoginPageTitle(), FrameworkConstants.LOGIN_PAGE_TITLE);
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
        loginPage.performLogin(getConfig().username(), getConfig().password());
        ScreenshotUtil.takeScreenshot();
    }
}
