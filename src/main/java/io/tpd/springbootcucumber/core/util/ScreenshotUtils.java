package io.tpd.springbootcucumber.core.util;

import io.tpd.springbootcucumber.core.element.WebTypifiedElement;
import lombok.AllArgsConstructor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.tpd.springbootcucumber.core.util.JSUtils.executeJavaScript;

public abstract class ScreenshotUtils {

    private static Logger logger = LoggerFactory.getLogger(WaitUtils.class.getSimpleName());

    public static void highlightElement(WebDriver driver, WebTypifiedElement element) {
        try {
            if (element.isPresent()) {
                executeJavaScript(driver, "arguments[0].style.border='3px solid red'", element);
            }
        } catch (TimeoutException te) {
            logger.trace("Timeout for highlighted element", te);
        }
    }

    public static void unhighlightElement(WebDriver driver, WebTypifiedElement element) {
        try {
            if (element.isPresent()) {
                executeJavaScript(driver, "arguments[0].style.removeProperty('border');", element);
            }
        } catch (TimeoutException te) {
            logger.trace("Timeout for highlighted element", te);
        }
    }

    //todo make static methods for screenShot - as soon as possible)
    @AllArgsConstructor
    public enum ScreenshotingType {
        FULL,
        PARTIAL,
        NO
    }

}
