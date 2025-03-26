package base;

import com.framework.constants.FrameworkConstants;
import com.framework.factory.PlaywrightFactory;
import com.framework.factory.PlaywrightManager;
import com.framework.utils.CommonUtil;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
  @BeforeSuite
  public void beforeSuite() {
    String path = CommonUtil.createResultsFolder();
    FrameworkConstants.setResultsFolder(path);
  }

  @BeforeMethod
  public void setup() {
    PlaywrightFactory.initializeBrowser(); // Creates thread-specific instances
  }

  @AfterMethod
  public void tearDown() {
    // Close context and page (browser/playwright remain for reuse)
    if (PlaywrightManager.getContext() != null) {
      PlaywrightManager.getContext().close();
    }
    PlaywrightManager.cleanup(); // Clear thread-local storage
  }

  @AfterSuite
  public void closeAllBrowsers() {
    // Close all browser instances across threads
    if (PlaywrightManager.getBrowser() != null) {
      PlaywrightManager.getBrowser().close();
    }
    if (PlaywrightManager.getPlaywright() != null) {
      PlaywrightManager.getPlaywright().close();
    }
  }

  protected Page getPage() {
    return PlaywrightManager.getPage();
  }
}