package com.framework.utils;

import com.framework.exceptions.PageActionsExceptions;
import com.framework.factory.PlaywrightManager;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import java.util.function.Supplier;

/**
 * PageActionHelper class.
 *
 * @Author: Hariharan Murugan
 * @CreatedDate: 26-Mar-2025 
 * @ModifiedDate: 26-Mar-2025
 */
@SuppressWarnings({"ALL", "unused"})
public class PageActionsHelper {

  /**
   * Default constructor for the PageActionsHelper class.
   */
  private PageActionsHelper() {
  }

  /**
   * Retrieves the current Playwright Page instance.
   *
   * @return the current Page instance
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025 
   * @ModifiedDate: 26-Mar-2025
   */
  public static Page getPage() {
    return PlaywrightManager.getPage();
  }

  /**
   * Retrieves a locator for the given CSS selector.
   *
   * @param selector the CSS selector to locate the element
   * @return the Locator for the specified selector
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025 
   * @ModifiedDate: 26-Mar-2025
   */
  public static Locator getLocator(String selector) {
    return getPage().locator(selector);
  }

  /**
   * Retrieves a locator for an element containing the specified text.
   *
   * @param text the text to locate the element
   * @return the Locator for the element containing the specified text
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static Locator getLocatorByText(String text) {
    return getPage().locator("text=" + text);
  }

  /**
   * Retrieves a visible locator for an element containing the specified text.
   *
   * @param text the text to locate the element
   * @return the visible Locator for the element containing the specified text
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static Locator getVisibleLocatorByText(String text) {
    return getPage().locator("text=" + text + ":visible");
  }

  /**
   * Retrieves a locator for the nth element matching the given CSS selector.
   *
   * @param selector the CSS selector to locate the elements
   * @param index    the index of the element to retrieve (0-based)
   * @return the Locator for the nth element matching the selector
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static Locator getNthLocator(String selector, int index) {
    return getPage().locator(selector + ":nth-of-type(" + (index + 1) + ")");
  }

  /**
   * Retrieves a locator for the given CSS selector.
   *
   * @param selector the CSS selector to locate the element
   * @return the Locator for the specified selector
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static Locator getLocatorByCss(String selector) {
    return getPage().locator(selector);
  }


  /**
   * Retrieves a locator for the given XPath expression.
   *
   * @param xpath the XPath expression to locate the element
   * @return the Locator for the specified XPath
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static Locator getLocatorByXpath(String xpath) {
    return getPage().locator(xpath);
  }

  /**
   * Retrieves a locator for an element with the specified ARIA role.
   *
   * @param role the ARIA role to locate the element
   * @return the Locator for the element with the specified role
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static Locator getLocatorByRole(String role) {
    return getPage().locator("role=" + role);
  }

  /**
   * Executes a given action and handles exceptions by wrapping them in a custom {@code PageActionsExceptions}.
   *
   * @param action       the action to execute, represented as a {@code Runnable}
   * @param errorMessage the error message to include in the exception if the action fails
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  private static void executeAction(Runnable action, String errorMessage) {
    try {
      action.run();
    } catch (TimeoutError e) {
      throw new PageActionsExceptions(errorMessage);
    } catch (Exception e) {
      throw new PageActionsExceptions(errorMessage, e);
    }
  }

  /**
   * Executes a given action and returns its result. If the action throws an exception,
   * it is wrapped in a custom {@code PageActionsExceptions} with a specified error message.
   *
   * @param <T>          the type of the result returned by the action
   * @param action       the action to execute, represented as a {@code Supplier}
   * @param errorMessage the error message to include in the exception if the action fails
   * @return the result of the executed action
   * @throws PageActionsExceptions if the action throws a {@code TimeoutError} or any other exception
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  private static <T> T executeAndReturnAction(Supplier<T> action, String errorMessage) {
    try {
      return action.get();
    } catch (TimeoutError e) {
      throw new PageActionsExceptions(errorMessage);  // Preserve exception cause
    } catch (Exception e) {
      throw new PageActionsExceptions(errorMessage, e);  // Handle other exceptions
    }
  }

  /**
   * Clicks on the element identified by the given CSS selector.
   *
   * @param selector the CSS selector of the element to click
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static void click(String selector) {
    Locator element = getPage().locator(selector);
    executeAction(element::click, "Click failed. Element not found: " + selector);
  }

  /**
   * Clicks on the visible element containing the specified text.
   *
   * @param text the text of the element to click
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static void clickByText(String text) {
    Locator element = getVisibleLocatorByText(text);
    executeAction(element::click, "Click failed. Element with text not found: " + text);
  }

  /**
   * Types the specified text into the input field identified by the given CSS selector.
   *
   * @param selector the CSS selector of the input field
   * @param text     the text to type into the input field
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static void type(String selector, String text) {
    Locator inputField = getLocator(selector);
    executeAction(() -> inputField.fill(text), "Click failed. Element with text not found: " + text);
  }

  /**
   * Clears the input field identified by the given CSS selector and types the specified text into it.
   *
   * @param selector the CSS selector of the input field
   * @param text     the text to type into the input field
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static void clearAndType(String selector, String text) {
    Locator inputField = getLocator(selector);
    executeAction(() -> {
      inputField.fill("");
      inputField.fill(text);
    }, "Clear and Type failed. Input field not found: " + selector);
  }

  /**
   * Selects an option from the dropdown identified by the given CSS selector.
   *
   * @param selector the CSS selector of the dropdown
   * @param value    the value of the option to select
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static void selectOption(String selector, String value) {
    Locator dropdown = getLocator(selector);
    executeAction(() -> dropdown.selectOption(value), "Select Option failed. Dropdown not found: " + selector);
  }

  /**
   * Hovers over the element identified by the given CSS selector.
   *
   * @param selector the CSS selector of the element to hover over
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static void hover(String selector) {
    Locator element = getLocator(selector);
    executeAction(element::hover, "Hover failed. Element not found: " + selector);
  }

  /**
   * Waits for the element identified by the given CSS selector to appear.
   *
   * @param selector the CSS selector of the element to wait for
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static void waitFor(String selector) {
    Locator element = getLocator(selector);
    executeAction(element::waitFor, "Wait for element failed. Element not found: " + selector);
  }

  /**
   * Scrolls to the element identified by the given CSS selector.
   *
   * @param selector the CSS selector of the element to scroll to
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static void scrollTo(String selector) {
    Locator element = getLocator(selector);
    executeAction(element::scrollIntoViewIfNeeded, "Scroll failed. Element not found: " + selector);
  }

  /**
   * Retrieves the text content of the element identified by the given CSS selector.
   *
   * @param selector the CSS selector of the element
   * @return the text content of the element
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static String getText(String selector) {
    Locator element = getLocator(selector);
    return executeAndReturnAction(element::textContent, "Get text failed. Element not found: " + selector);
  }

  /**
   * Retrieves the value of the input field identified by the given CSS selector.
   *
   * @param selector the CSS selector of the input field
   * @return the value of the input field
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static String getValue(String selector) {
    Locator element = getLocator(selector);
    return executeAndReturnAction(element::inputValue, "Get value failed. Element not found: " + selector);
  }

  /**
   * Checks if the element identified by the given CSS selector is visible.
   *
   * @param selector the CSS selector of the element
   * @return true if the element is visible, false otherwise
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static boolean isVisible(String selector) {
    Locator element = getLocator(selector);
    return element.isVisible();
  }

  /**
   * Checks if the element identified by the given CSS selector is enabled.
   *
   * @param selector the CSS selector of the element
   * @return true if the element is enabled, false otherwise
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static boolean isEnabled(String selector) {
    Locator element = getLocator(selector);
    return element.isEnabled();
  }

  /**
   * Checks if the checkbox identified by the given CSS selector is checked.
   *
   * @param selector the CSS selector of the checkbox
   * @return true if the checkbox is checked, false otherwise
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static boolean isChecked(String selector) {
    Locator checkbox = getLocator(selector);
    return checkbox.isChecked();
  }

  /**
   * Toggles the state of the checkbox identified by the given CSS selector.
   *
   * @param selector the CSS selector of the checkbox
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static void toggleCheckbox(String selector) {
    Locator checkbox = getLocator(selector);
    try {
      checkbox.click();
    } catch (TimeoutError e) {
      throw new PageActionsExceptions("Toggle checkbox failed. Checkbox not found: " + selector);
    }
  }

  /**
   * Waits for a network request matching the specified URL pattern to complete.
   *
   * @param urlPattern the URL pattern to match the network request
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
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

  /**
   * Accepts an alert dialog on the page.
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static void acceptAlert() {
    getPage().onDialog(Dialog::accept);
  }

  /**
   * Dismisses an alert dialog on the page.
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static void dismissAlert() {
    getPage().onDialog(Dialog::dismiss);
  }

  /**
   * Handles a popup by closing it.
   *
   * @Author: Hariharan Murugan
   * @CreatedDate: 26-Mar-2025
   * @ModifiedDate: 26-Mar-2025
   */
  public static void handlePopup() {
    getPage().onPopup(Page::close);
  }

}
