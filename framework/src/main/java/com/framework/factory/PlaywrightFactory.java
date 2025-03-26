package com.framework.factory;

import com.framework.constants.FrameworkConstants;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlaywrightFactory {

  public static void initializeBrowser() {
    String browserName = ConfigFactory.getConfig().browser().toLowerCase();

    Playwright playwright = Playwright.create();
    Browser browser = createBrowser(playwright, browserName);
    BrowserContext context = createContext(browser); // Now properly defined
    Page page = context.newPage();

    PlaywrightManager.setPlaywright(playwright);
    PlaywrightManager.setBrowser(browser);
    PlaywrightManager.setContext(context);
    PlaywrightManager.setPage(page);
  }

  private static Browser createBrowser(Playwright playwright, String browserName) {
    BrowserType browserType;
    switch (browserName.toLowerCase()) {
      case "chromium":
      case "chrome":
      case "edge":
        browserType = playwright.chromium();
        break;
      case "firefox":
        browserType = playwright.firefox();
        break;
      case "webkit":
        browserType = playwright.webkit();
        break;
      default:
        throw new IllegalArgumentException("Invalid browser: " + browserName);
    }
    return browserType.launch(createLaunchOptions());
  }

  // Fixed: Added createContext method
  private static BrowserContext createContext(Browser browser) {
    return browser.newContext(createContextOptions());
  }

  private static Browser.NewContextOptions createContextOptions() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Path videoPath = Paths.get(FrameworkConstants.getResultsFolder(), "videos");
    return new Browser.NewContextOptions()
      .setViewportSize((int) screenSize.getWidth(), (int) screenSize.getHeight())
      .setRecordVideoDir(videoPath)
      .setRecordVideoSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
  }

  private static BrowserType.LaunchOptions createLaunchOptions() {
    return new BrowserType.LaunchOptions()
      .setHeadless(ConfigFactory.getConfig().headless())
      .setTimeout(ConfigFactory.getConfig().timeout());
  }
}