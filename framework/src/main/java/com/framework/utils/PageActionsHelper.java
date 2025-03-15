package com.framework.utils;

import com.framework.exceptions.PageActionsExceptions;
import com.framework.factory.PlaywrightFactoryold;
import com.framework.factory.PlaywrightManager;
import com.microsoft.playwright.*;

import java.util.function.Supplier;

public class PageActionsHelper {

    public static Page getPage() {
        return PlaywrightManager.getPage();
    }

    public PageActionsHelper() {
    }

    public static Locator getLocator(String selector) {
        return getPage().locator(selector);
    }

    public static Locator getLocatorByText(String text) {
        return getPage().locator("text=" + text);
    }

    public static Locator getVisibleLocatorByText(String text) {
        return getPage().locator("text=" + text + ":visible");
    }

    public static Locator getNthLocator(String selector, int index) {
        return getPage().locator(selector + ":nth-of-type(" + (index + 1) + ")");
    }

    public static Locator getLocatorByCss(String selector) {
        return getPage().locator(selector);
    }

    public static Locator getLocatorByXPath(String xpath) {
        return getPage().locator(xpath);
    }

    public static Locator getLocatorByRole(String role) {
        return getPage().locator("role=" + role);
    }

    //----------------------------------------------------------------------------------------------------------------------------------

    private static void executeAction(Runnable action, String errorMessage) {
        try {
            action.run();
        } catch (TimeoutError e) {
            throw new PageActionsExceptions(errorMessage);
        } catch (Exception e) {
            throw new PageActionsExceptions(errorMessage, e);
        }
    }

    private static <T> T executeAndReturnAction(Supplier<T> action, String errorMessage) {
        try {
            return action.get();
        } catch (TimeoutError e) {
            throw new PageActionsExceptions(errorMessage);  // Preserve exception cause
        } catch (Exception e) {
            throw new PageActionsExceptions(errorMessage, e);  // Handle other exceptions
        }
    }

    public static void click(String selector) {
        Locator element = getPage().locator(selector);
        executeAction(element::click, "Click failed. Element not found: " + selector);
    }

    public static void clickByText(String text) {
        Locator element = getVisibleLocatorByText(text);
        executeAction(element::click, "Click failed. Element with text not found: " + text);
    }

    public static void type(String selector, String text) {
        Locator inputField = getLocator(selector);
        executeAction(() -> inputField.fill(text), "Click failed. Element with text not found: " + text);
    }

    public static void clearAndType(String selector, String text) {
        Locator inputField = getLocator(selector);
        executeAction(() -> {
            inputField.fill("");
            inputField.fill(text);
        }, "Clear and Type failed. Input field not found: " + selector);
    }

    public static void selectOption(String selector, String value) {
        Locator dropdown = getLocator(selector);
        executeAction(() -> dropdown.selectOption(value), "Select Option failed. Dropdown not found: " + selector);
    }

    public static void hover(String selector) {
        Locator element = getLocator(selector);
        executeAction(element::hover, "Hover failed. Element not found: " + selector);
    }

    public static void waitFor(String selector) {
        Locator element = getLocator(selector);
        executeAction(element::waitFor, "Wait for element failed. Element not found: " + selector);
    }

    public static void scrollTo(String selector) {
        Locator element = getLocator(selector);
        executeAction(element::scrollIntoViewIfNeeded, "Scroll failed. Element not found: " + selector);
    }

    public static String getText(String selector) {
        Locator element = getLocator(selector);
        return executeAndReturnAction(element::textContent,"Get text failed. Element not found: " + selector);
    }

    public static String getValue(String selector) {
        Locator element = getLocator(selector);
        return executeAndReturnAction(element::inputValue, "Get value failed. Element not found: " + selector);
    }

    public static boolean isVisible(String selector) {
        Locator element = getLocator(selector);
        return element.isVisible();
    }

    public static boolean isEnabled(String selector) {
        Locator element = getLocator(selector);
        return element.isEnabled();
    }

    public static boolean isChecked(String selector) {
        Locator checkbox = getLocator(selector);
        return checkbox.isChecked();
    }

    public static void toggleCheckbox(String selector) {
        Locator checkbox = getLocator(selector);
        try {
            checkbox.click();
        } catch (TimeoutError e) {
            throw new PageActionsExceptions("Toggle checkbox failed. Checkbox not found: " + selector);
        }
    }

    public static void waitForRequest(String urlPattern) {
        try {
            getPage().onRequest(request -> {
                if (request.url().contains(urlPattern)) {
                    throw new PageActionsExceptions("Request matching the pattern: " + urlPattern + " was made.");
                }
            });
            getPage().waitForTimeout(5000);
        } catch (TimeoutError e) {
            throw new PageActionsExceptions("Network request with URL pattern " + urlPattern + " did not complete.");
        }
    }

    public static void acceptAlert() {
        getPage().onDialog(Dialog::accept);
    }

    public static void dismissAlert() {
        getPage().onDialog(Dialog::dismiss);
    }

    public static void handlePopup() {
        getPage().onPopup(Page::close);
    }

}
