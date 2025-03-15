package com.company.pages;

import com.framework.factory.PlaywrightFactory;
import com.framework.utils.DynamicXpathUtil;
import com.microsoft.playwright.Page;

import static com.framework.utils.PageActionsHelper.click;
import static com.framework.utils.PageActionsHelper.waitFor;

public class HomePage {

    private Page page;

    // 1. String Locators - OR
    private String search = "input[name='search']";
    private String searchIcon = "div#search button";
    private String searchPageHeader = "div#content h1";
    private String loginLink = "a:text('Login')";
    private String myAccountLink = "a[title='My Account']";
    private String menuItems = "//a[@class='oxd-main-menu-item']/span[text()='%s']";
    private String lblEmployeeInformation = "//h5[text()='Employee Information']";


    // 2. page constructor:
    public HomePage() {
        page = PlaywrightFactory.getPage();
    }

    // 3. page actions/methods:
    public String getHomePageTitle() {
        String title =  page.title();
        System.out.println("page title: " + title);
        return title;
    }

    public String getHomePageURL() {
        String url =  page.url();
        System.out.println("page url : " + url);
        return url;
    }
    public void navigateToPIM() {
        String pim = DynamicXpathUtil.getXpath(menuItems, "PIM");
        waitFor(pim);
        click(pim);
        waitFor(lblEmployeeInformation);
    }




}