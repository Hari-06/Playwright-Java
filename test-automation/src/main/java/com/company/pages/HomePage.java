package com.company.pages;

import static com.framework.utils.PageActionsHelper.click;
import static com.framework.utils.PageActionsHelper.waitFor;

import com.framework.utils.DynamicXpathUtil;
import com.microsoft.playwright.Page;

public class HomePage {

  private final Page page;

  // 1. String Locators - OR
  private final String menuItems = "//a[@class='oxd-main-menu-item']/span[text()='%s']";
  private final String lblEmployeeInformation = "//h5[text()='Employee Information']";


  // 2. page constructor:
  public HomePage(Page page) {
    this.page = page;
  }

  // 3. page actions/methods:
  public String getHomePageTitle() {
    String title = page.title();
    System.out.println("page title: " + title);
    return title;
  }

  public String getHomePageURL() {
    String url = page.url();
    System.out.println("page url : " + url);
    return url;
  }

  public Object navigateTo(String menu) {
    switch (menu) {
      case "PIM":
        String pim = DynamicXpathUtil.getXpath(menuItems, menu);
        waitFor(pim);
        click(pim);
        return new PIMPage(page);
      case "Admin":
        String admin = DynamicXpathUtil.getXpath(menuItems, menu);
        waitFor(admin);
        click(admin);
        return new AdminPage(page);
      default:
        return null;
    }
  }

}
