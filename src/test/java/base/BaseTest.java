package base;

import org.testng.annotations.*;
import com.microsoft.playwright.Page;
import com.framework.factory.PlaywrightFactory;
import com.company.pages.HomePage;

public class BaseTest {

    PlaywrightFactory pf;
    Page page;

    protected HomePage homePage;

    @BeforeTest
    public void setup() {
        pf = new PlaywrightFactory();
        page = pf.initBrowser();
        PlaywrightFactory.setPage(page);
    }

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }

}