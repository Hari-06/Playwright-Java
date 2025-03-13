package com.company.pages;

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

    // 3. page actions/methods:
    private void setEmailId(String appUserName) {
        clearAndType(username,appUserName);
    }

    private void setPassword(String appPassword) {
        clearAndType(password,appPassword);
    }

    private void clickLoginBtn() {
        click(loginBtn);
    }

    public String getLoginPageTitle() {
        waitFor(username);
        return page.title();
    }

    public boolean isForgotPwdLinkExist() {
        waitFor(forgotPwdLink);
        return isVisible(forgotPwdLink);
    }

    public boolean performLogin(String appUserName, String appPassword) {
        clearAndType(username,appUserName);
        clearAndType(password,appPassword);
        click(loginBtn);
        waitFor(search);
        if(isVisible(search)) {
            System.out.println("user is logged in successfully....");
            return true;
        }else {
            System.out.println("user is not logged in successfully....");
            return false;
        }
    }

    public void navigate(String url) {
        page.navigate(url);
    }
}