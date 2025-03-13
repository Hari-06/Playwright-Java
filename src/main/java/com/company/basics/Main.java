package com.company.basics;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.*;
import com.microsoft.playwright.Browser.*;
import com.microsoft.playwright.options.AriaRole;

import java.awt.*;
import java.nio.file.Paths;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

//        Playwright playwright = Playwright.create();
//        LaunchOptions launchOptions = new LaunchOptions();
//        launchOptions.setHeadless(false);
//        launchOptions.setTimeout(500);
//        launchOptions.setChannel("msedge");
//        Browser browser = playwright.chromium().launch(launchOptions);
//        NewContextOptions options = new NewContextOptions();
//        options.setViewportSize(width, height);
//        options.setRecordVideoDir(Paths.get("myVideos/"));
//        options.setRecordVideoSize(width,height);
//        BrowserContext context = browser.newContext(options);
//        // Start tracing before creating / navigating a page.
//        context.tracing().start(new Tracing.StartOptions()
//                .setScreenshots(true)
//                .setSnapshots(true)
//                .setSources(true));
//        Page page = context.newPage();
//        page.navigate("https://www.amazon.com");
//        String title = page.title();
//
//        System.out.println("Page title is: " +title);
//
//        // Stop tracing and export it into a zip archive.
//        context.tracing().stop(new Tracing.StopOptions()
//                .setPath(Paths.get("trace.zip")));
//        browser.close();
//        playwright.close();

        try (Playwright playwright = Playwright.create()) {
            LaunchOptions launchOptions = new LaunchOptions();            launchOptions.setHeadless(false);
            launchOptions.setTimeout(500);
            launchOptions.setChannel("msedge");

            Browser browser = playwright.chromium().launch(launchOptions);

            NewContextOptions options = new NewContextOptions();
            options.setViewportSize(width, height);
            options.setRecordVideoDir(Paths.get("myVideos/"));
            options.setRecordVideoSize(width,height);

            BrowserContext context = browser.newContext(options);
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));

            Page page = context.newPage();
            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            page.getByPlaceholder("Username").click();
            page.getByPlaceholder("Username").fill("Admin");
            page.getByPlaceholder("Username").press("Tab");
            page.getByPlaceholder("Password").fill("admin123");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Leave")).click();
            page.locator(".oxd-select-text--after > .oxd-icon").first().click();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Taken")).click();
            page.getByText("-- Select --").nth(1).click();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("CAN - Personal")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
            page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("")).locator("i").click();
            page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(" 2025-19-02 to 2025-28-02")).click();

            context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
            browser.close();

        }


        //mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen https://www.amazon.com"
    }
}