package com.framework.factory;

import com.framework.utils.CommonUtils;
import com.microsoft.playwright.*;

import static com.framework.factory.ConfigFactory.getConfig;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PolicySpi;
import java.util.Objects;

public class PlaywrightFactory {
    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    public static Playwright getPlaywright() {
        return playwright.get();
    }
    public static Browser getBrowser() {
        return browser.get();
    }
    public static BrowserContext getBrowserContext() {
        return browserContext.get();
    }
    public static Page getPage() {
        return page.get();
    }

    public static void unloadPlaywright() {
        playwright.remove();
    }
    public static void unloadBrowser() {
        browser.remove();
    }
    public static void unloadBrowserContext() {
        browserContext.remove();
    }
    public static void unloadPage() {
        page.remove();
    }




    public static void setPage(Page page) {
        if(Objects.nonNull(page)) {
            PlaywrightFactory.page.set(page);
        }
    }

    private BrowserType.LaunchOptions getLaunchOptions() {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(false);
        launchOptions.setTimeout(getConfig().timeout());
        return launchOptions;
    }
    private Browser.NewContextOptions getNewContextOptions(String folder) {
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        contextOptions.setViewportSize(width, height);
        Path videoDirectoryPath = Paths.get(folder + "//" +getConfig().videoDirectory());
        contextOptions.setRecordVideoDir(videoDirectoryPath);
        contextOptions.setRecordVideoSize(width,height);
        return contextOptions;
    }
    public static BrowserContext startTracing() {
        getBrowserContext().tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        return getBrowserContext();
    }
    public static void stopTracing(Path tracingDirectoryPath) {
        getBrowserContext().tracing().stop(new Tracing.StopOptions()
                .setPath(tracingDirectoryPath));
    }
    public Page initBrowser() {
        String folder = System.getProperty("user.dir") + "\\" + CommonUtils.createFolder();
        playwright.set(Playwright.create());
        String browserName = getConfig().browser();
        switch (browserName.toLowerCase()) {
            case "chromium":
                browser.set(getPlaywright().chromium().launch(getLaunchOptions()));
                break;
            case "firefox":
                browser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
                browser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome":
                browser.set(
                        getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;
            case "edge":
                browser.set(
                        getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false)));
                break;

            default:
                System.out.println("please pass the right browser name......");
                break;
        }

        browserContext.set(getBrowser().newContext(getNewContextOptions(folder)));
        page.set(getBrowserContext().newPage());
        return getPage();
    }
}
