package base;

import com.framework.constants.FrameworkConstants;
import com.framework.factory.PlaywrightFactoryold;
import com.framework.manager.PlaywrightDriver;
import com.framework.utils.CommonUtils;
import com.microsoft.playwright.Page;
import org.testng.annotations.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.framework.factory.ConfigFactory.getConfig;

public class BaseTest {

    protected PlaywrightFactoryold playwrightFactory;
    protected Page page;
    @BeforeSuite
    public void beforeSuite() {
        String path = CommonUtils.createResultsFolder();
        FrameworkConstants.setResultsFolder(path);
    }

    @BeforeMethod
    public void setup() {
        /*playwrightFactory = new PlaywrightFactoryold();
        page = playwrightFactory.initBrowser();
        PlaywrightFactoryold.setPage(page);
        PlaywrightFactoryold.startTracing();*/
        PlaywrightDriver.initDriver();
    }

    @AfterMethod
    public void tearDown() {
//        Path tracingDirectoryPath = Paths.get( FrameworkConstants.getResultsFolder()+"//"+ getConfig().tracingDirectory()+"//traces.zip");
//        PlaywrightFactoryold.stopTracing(tracingDirectoryPath);
//        page.context().browser().close();
//        PlaywrightFactoryold.unloadPage();
//        PlaywrightFactoryold.unloadBrowserContext();
//        PlaywrightFactoryold.unloadBrowser();
//        PlaywrightFactoryold.unloadPlaywright();
        PlaywrightDriver.quitDriver();
    }

}