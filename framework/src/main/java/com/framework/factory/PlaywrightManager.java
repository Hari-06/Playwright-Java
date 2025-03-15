package com.framework.factory;

import com.microsoft.playwright.*;

public class PlaywrightManager {
    private static final ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> tlContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> tlPage = new ThreadLocal<>();

    private PlaywrightManager() {
    }

    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    public static void setPlaywright(Playwright playwright) {
        tlPlaywright.set(playwright);
    }

    public static Browser getBrowser() {
        return tlBrowser.get();
    }

    public static void setBrowser(Browser b) {
        tlBrowser.set(b);
    }

    public static BrowserContext getContext() {
        return tlContext.get();
    }

    public static void setContext(BrowserContext browserContext) {
        tlContext.set(browserContext);
    }

    public static Page getPage() {
        return tlPage.get();
    }

    public static void setPage(Page page) {
        tlPage.set(page);
    }

    public static void cleanup() {
        tlPage.remove();
        tlContext.remove();
        tlBrowser.remove();
        tlPlaywright.remove();
    }
}