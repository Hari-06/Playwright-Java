package playwright.basics;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.nio.file.Paths;
import java.util.Base64;

@Listeners(ChainTestListener.class)
public class SampleTest {
    @Test
    public void sum() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        try (Playwright playwright = Playwright.create()) {
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
            launchOptions.setHeadless(true);
            launchOptions.setTimeout(500);
            launchOptions.setChannel("chromium");

            Browser browser = playwright.chromium().launch(launchOptions);

            Browser.NewContextOptions options = new Browser.NewContextOptions();
            options.setViewportSize(width, height);
            options.setRecordVideoDir(Paths.get("myVideos/"));
            options.setRecordVideoSize(width,height);

            BrowserContext context = browser.newContext(options);
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));

            Page page = context.newPage();
            page.navigate("https://opensource-demo.orangehrmlive.com/");
            page.getByPlaceholder("Username").click();
            page.getByPlaceholder("Username").fill("Admin");
            page.getByPlaceholder("Username").press("Tab");
            page.getByPlaceholder("Password").fill("admin123");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Leave")).click();
            Thread.sleep(3000);
            Assert.assertTrue(true);
            ChainTestListener.log("log example");
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshot.png"))
                    .setFullPage(true));

            byte[] buffer = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            ChainTestListener.embed(buffer, "image/png");

            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));
            browser.close();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
