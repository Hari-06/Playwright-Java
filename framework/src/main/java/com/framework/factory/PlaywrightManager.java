package com.framework.factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.Objects;

public class PlaywrightManager {
    private PlaywrightManager() {}

    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    public static Playwright getPlaywright() {
        return playwright.get();
    }

    public static Browser getBrowser() {
        return browser.get();
    }

    public static BrowserContext getContext() {
        return context.get();
    }

    public static Page getPage() {
        return page.get();
    }

    static void setPlaywright(Playwright instance) {
        if (Objects.nonNull(instance)) {
            playwright.set(instance);
        }
    }

    static void setBrowser(Browser instance) {
        if (Objects.nonNull(instance)) {
            browser.set(instance);
        }
    }

    static void setContext(BrowserContext instance) {
        if (Objects.nonNull(instance)) {
            context.set(instance);
        }
    }

    static void setPage(Page instance) {
        if (Objects.nonNull(instance)) {
            page.set(instance);
        }
    }

    public static void unload() {
        playwright.remove();
        browser.remove();
        context.remove();
        page.remove();
    }
}
