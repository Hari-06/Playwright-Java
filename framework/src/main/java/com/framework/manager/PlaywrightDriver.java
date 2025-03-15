package com.framework.manager;

import com.framework.exceptions.FrameworkException;
import com.framework.factory.PlaywrightFactory;
import com.framework.factory.PlaywrightManager;
import com.microsoft.playwright.Page;

import static com.framework.factory.PlaywrightFactoryold.setPage;

public class PlaywrightDriver {
    private PlaywrightDriver() {}

    /*public static void initDriver() {
        if (PlaywrightManager.getPage() == null) {
            try {
                setPage(PlaywrightFactory.initBrowser());
            } catch (Exception e) {
                throw new FrameworkException("Playwright initialization failed", e);
            }
        }
    }

    public static void quitDriver() {
        if (PlaywrightManager.getPage() != null) {
            PlaywrightManager.getPage().close();
            PlaywrightManager.getContext().close();
            PlaywrightManager.getBrowser().close();
            PlaywrightManager.getPlaywright().close();
            PlaywrightManager.unload();
        }
    }*/
}
