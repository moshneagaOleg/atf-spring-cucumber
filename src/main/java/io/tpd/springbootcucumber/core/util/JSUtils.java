package io.tpd.springbootcucumber.core.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class JSUtils {

    private static final String SCROLL_TO_ELEMENT_INTO_MIDDLE = "var viewPortHeight = "
            + "Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
            + "var elementTop = arguments[0].getBoundingClientRect().top;"
            + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

    public static Boolean executeJavaScript(WebDriver driver, String script) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(script);
    }

    public static Boolean executeJavaScript(WebDriver driver, String script, WebElement... elements) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(script, elements);
    }

    public static void scrollTo(WebDriver driver, WebElement element) {
        executeJavaScript(driver, SCROLL_TO_ELEMENT_INTO_MIDDLE, element);
    }

}
