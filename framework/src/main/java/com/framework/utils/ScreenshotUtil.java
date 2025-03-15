package com.framework.utils;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.framework.factory.PlaywrightFactory;
import org.testng.annotations.Listeners;

import java.util.Base64;

@Listeners(ChainTestListener.class)
public class ScreenshotUtil {
    private ScreenshotUtil() {}
    public static void takeScreenshot() {
        byte[] buffer = PlaywrightFactory.getPage().screenshot();
        ChainTestListener.embed(Base64.getEncoder().encodeToString(buffer),"image/png");
    }
}
