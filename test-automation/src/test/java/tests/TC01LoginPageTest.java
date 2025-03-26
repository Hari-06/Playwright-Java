package tests;

import static com.framework.factory.ConfigFactory.getConfig;

import base.BaseTest;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.company.pages.LoginPage;
import com.framework.constants.FrameworkConstants;
import com.framework.factory.PlaywrightManager;
import com.framework.utils.ScreenshotUtil;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ChainTestListener.class)
public class TC01LoginPageTest extends BaseTest {
  protected static LoginPage loginPage;

  private TC01LoginPageTest() {
  }

  @Test()
  public void loginTest1(Map<String, String> data) {
    String username = data.get("username");
    String password = data.get("password");
    loginPage = new LoginPage(PlaywrightManager.getPage());
    loginPage.navigate(getConfig().url());
    Assert.assertEquals(loginPage.getLoginPageTitle(), FrameworkConstants.LOGIN_PAGE_TITLE);
    Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    loginPage.performLogin(username, password);
    ScreenshotUtil.takeScreenshot();
  }
}
