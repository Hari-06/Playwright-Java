package com.framework.utils;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.framework.factory.PlaywrightManager;
import com.microsoft.playwright.Page;
import java.util.Base64;
import org.testng.annotations.Listeners;

@Listeners(ChainTestListener.class)
public class ScreenshotUtil {
  private ScreenshotUtil() {
  }

  public static Page getPage() {
    return PlaywrightManager.getPage();
  }

  public static void takeScreenshot() {
    byte[] buffer = getPage().screenshot();
    ChainTestListener.embed(Base64.getEncoder().encodeToString(buffer), "image/png");
  }
}
