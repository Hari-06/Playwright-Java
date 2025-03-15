package base;

import com.framework.factory.PlaywrightFactory;
import com.framework.utils.CommonUtils;
import com.microsoft.playwright.Page;
import org.testng.annotations.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.framework.factory.ConfigFactory.getConfig;

public class BaseTest {

    PlaywrightFactory pf;
    Page page;
    String resultFolder;

    // PlaywrightFactory.loadPlaywright();
    //        PlaywrightFactory.loadBrowser();
    //        PlaywrightFactory.loadBrowserContext();
    @BeforeSuite
    public void beforeSuite() {
        resultFolder = CommonUtils.createFolder();
    }

    @AfterSuite
    public void afterSuite() {
        PlaywrightFactory.unloadPage();
        PlaywrightFactory.unloadBrowserContext();
        PlaywrightFactory.unloadBrowser();
        PlaywrightFactory.unloadPlaywright();
    }

    @BeforeTest
    public void setup() {
        pf = new PlaywrightFactory();
        page = pf.initBrowser();
        PlaywrightFactory.setPage(page);
        PlaywrightFactory.startTracing();
    }

    @AfterTest
    public void tearDown() {
        Path tracingDirectoryPath = Paths.get( resultFolder+"//"+ getConfig().tracingDirectory()+"//traces.zip");
        PlaywrightFactory.stopTracing(tracingDirectoryPath);
        page.context().browser().close();
    }
    @AfterClass
    public void afterClass() {
    }

}