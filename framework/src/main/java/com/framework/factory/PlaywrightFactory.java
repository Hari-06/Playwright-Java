package com.framework.factory;

import com.framework.constants.FrameworkConstants;
import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.framework.factory.ConfigFactory.getConfig;

public class PlaywrightFactory {
    private PlaywrightFactory() {}

    public static Page initBrowser() {
        String browserName = getConfig().browser().toLowerCase();

        Playwright playwright = Playwright.create();
        Browser browser = createBrowser(playwright, browserName);
        BrowserContext context = createContext(browser);
        Page page = context.newPage();

        PlaywrightManager.setPlaywright(playwright);
        PlaywrightManager.setBrowser(browser);
        PlaywrightManager.setContext(context);
        PlaywrightManager.setPage(page);

        return page;
    }

    private static Browser createBrowser(Playwright playwright, String browserName) {
        switch (browserName.toLowerCase()) {
            case "chromium":
            case "chrome":
            case "edge":
                return playwright.chromium().launch(createLaunchOptions());
            case "firefox":
                return playwright.firefox().launch(createLaunchOptions());
            case "webkit":
                return playwright.webkit().launch(createLaunchOptions());
            default:
                throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }
    }
    private static BrowserType.LaunchOptions createLaunchOptions() {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(getConfig().headless());
        launchOptions.setTimeout(getConfig().timeout());
        return launchOptions;
    }
    private static BrowserContext createContext(Browser browser) {
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        Path videoDirectoryPath = Paths.get(FrameworkConstants.getResultsFolder() + "//" +getConfig().videoDirectory());
        return browser.newContext(new Browser.NewContextOptions()
        .setViewportSize(width, height)
                .setRecordVideoDir(videoDirectoryPath)
                .setRecordVideoSize(width,height));
    }

}
