package com.company.pages;

import com.framework.factory.PlaywrightManager;
import com.microsoft.playwright.Page;
import static com.framework.utils.PageActionsHelper.*;

public class LoginPage {

    private Page page;

    // 1. String Locators - OR
    private String username = "//input[@placeholder='Username']";
    private String password = "//input[@placeholder='Password']";
    private String loginBtn = "//button[text()=' Login ']";
    private String search = "//input[@placeholder='Search']";
    private String forgotPwdLink = "//div[@class='orangehrm-login-forgot']/p";

    // 2. page constructor:
    public LoginPage(Page page) {
        this.page = page;
    }

    public String getLoginPageTitle() {
        waitFor(username);
        return page.title();
    }

    public boolean isForgotPwdLinkExist() {
        waitFor(forgotPwdLink);
        return isVisible(forgotPwdLink);
    }

    public HomePage performLogin(String appUserName, String appPassword) {
        clearAndType(username,appUserName);
        clearAndType(password,appPassword);
        click(loginBtn);
        waitFor(search);
        if(isVisible(search)) {
            System.out.println("user is logged in successfully....");
            return new HomePage(page);
        }else {
            System.out.println("user is not logged in successfully....");
            return null;
        }
    }

    public void navigate(String url) {
        page.navigate(url);
    }
}