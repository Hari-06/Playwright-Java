package tests;

import static com.framework.factory.ConfigFactory.getConfig;

import base.BaseTest;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.company.pages.HomePage;
import com.company.pages.LoginPage;
import com.framework.constants.FrameworkConstants;
import com.framework.utils.ScreenshotUtil;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ChainTestListener.class)
public class TC02_HomePageTest extends BaseTest {

  private TC02_HomePageTest() {
  }

  @Test(priority = 1, groups = {"Sanity"})
  public void loginPageNavigationTest(Map<String, String> data) {
    String username = data.get("username");
    String password = data.get("password");
    LoginPage loginPage;
    HomePage homePage;
    loginPage = new LoginPage(getPage());
    loginPage.navigate(getConfig().url());
    loginPage.getLoginPageTitle();
    homePage = loginPage.performLogin(username, password);
    Assert.assertEquals(homePage.getHomePageTitle(), FrameworkConstants.HOME_PAGE_TITLE);
    Assert.assertEquals(homePage.getHomePageURL(),
      "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    homePage.navigateTo("PIM");
    ScreenshotUtil.takeScreenshot();
  }

  @Test(priority = 2, groups = {"Sanity"})
  public void loginPageNavigationTest2(Map<String, String> data) {
    String username = data.get("username");
    String password = data.get("password");
    LoginPage loginPage;
    HomePage homePage;
    loginPage = new LoginPage(getPage());
    loginPage.navigate(getConfig().url());
    loginPage.getLoginPageTitle();
    homePage = loginPage.performLogin(username, password);
    Assert.assertEquals(homePage.getHomePageTitle(), FrameworkConstants.HOME_PAGE_TITLE);
    Assert.assertEquals(homePage.getHomePageURL(),
      "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    homePage.navigateTo("PIM");
    ScreenshotUtil.takeScreenshot();
  }

}