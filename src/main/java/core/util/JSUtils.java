package core.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class JSUtils {

    public static Boolean executeJavaScript(WebDriver driver, String script) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(script);
    }

    public static Boolean executeJavaScript(WebDriver driver, String script, WebElement... elements) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(script, elements);
    }

}
