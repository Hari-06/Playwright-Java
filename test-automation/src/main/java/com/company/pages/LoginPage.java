package com.company.pages;

import static com.framework.utils.PageActionsHelper.clearAndType;
import static com.framework.utils.PageActionsHelper.click;
import static com.framework.utils.PageActionsHelper.isVisible;
import static com.framework.utils.PageActionsHelper.waitFor;

import com.microsoft.playwright.Page;

public class LoginPage {

  private final Page page;

  // 1. String Locators - OR
  private final String username = "//input[@placeholder='Username']";
  private final String password = "//input[@placeholder='Password']";
  private final String loginBtn = "//button[text()=' Login ']";
  private final String search = "//input[@placeholder='Search']";
  private final String forgotPwdLink = "//div[@class='orangehrm-login-forgot']/p";

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
    clearAndType(username, appUserName);
    clearAndType(password, appPassword);
    click(loginBtn);
    waitFor(search);
    if (isVisible(search)) {
      System.out.println("user is logged in successfully....");
      return new HomePage(page);
    } else {
      System.out.println("user is not logged in successfully....");
      return null;
    }
  }

  public void navigate(String url) {
    page.navigate(url);
  }
}
