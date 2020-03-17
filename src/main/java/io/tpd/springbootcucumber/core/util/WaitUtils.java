package io.tpd.springbootcucumber.core.util;

import io.tpd.springbootcucumber.core.element.WebTypifiedElement;
import lombok.SneakyThrows;
import org.hamcrest.Matcher;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.Thread.sleep;

public abstract class WaitUtils {

    private static final long DEFAULT_RETRY_INTERVAL_MS = 1000;
    private static Logger logger = LoggerFactory.getLogger(WaitUtils.class.getSimpleName());

    public static Boolean waitUntilCondition(Supplier<Boolean> function, boolean condition, int secondsTimeInterval) {
        boolean result = !condition;
        long start = System.currentTimeMillis();

        while ((System.currentTimeMillis() - start) < TimeUnit.SECONDS.toMillis(secondsTimeInterval) && result != condition) {
            result = function.get();
            waitForRetry(DEFAULT_RETRY_INTERVAL_MS);
        }

        return result;
    }

    public static Boolean waitUntilCondition(
            Function<WebTypifiedElement, Boolean> function,
            WebTypifiedElement webElement, boolean condition, int secondsTimeInterval) {
        long start = System.currentTimeMillis();
        do {
            try {
                if (function.apply(webElement) == condition) return condition;
            } catch (TimeoutException e) {
                logger.info("exception caught throughout 'waitUntilCondition' method execution: {}", e.getMessage());
            }
            waitForRetry(DEFAULT_RETRY_INTERVAL_MS);
        } while ((System.currentTimeMillis() - start) < TimeUnit.SECONDS.toMillis(secondsTimeInterval));
        throw new RuntimeException("condition wasn't met within indicated time range");
    }

    @SneakyThrows
    public static <T> T waitUntilCondition(Supplier<T> supplier, Matcher<? super T> matcher, int secondsTimeInterval) {
        long start = System.currentTimeMillis();
        do {
            T result = supplier.get();
            if (matcher.matches(result)) {
                return result;
            }
            waitForRetry(DEFAULT_RETRY_INTERVAL_MS);
        } while ((System.currentTimeMillis() - start) < TimeUnit.SECONDS.toMillis(secondsTimeInterval));
        throw new RuntimeException("condition wasn't met within indicated time range");
    }

    public static void waitForRetry(long retryDelayMs) {
        try {
            sleep(retryDelayMs);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread sleep in the retry interval was interrupted", e);
        }
    }

    public static <T extends WebTypifiedElement> T waitFor(WebDriver driver, T element, int seconds) {
        new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
        element.isDisplayedAssertion();
        return element;
    }

    public static <T extends TypifiedElement> T waitFor(WebDriver driver, T element, int seconds) {
        new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public static <T extends WebTypifiedElement> T waitForClickable(WebDriver driver, T element, int seconds) {
        new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(element));
        element.isDisplayedAssertion();
        return element;
    }

    public static <T extends TypifiedElement> T waitForClickable(WebDriver driver, T element, int seconds) {
        new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

}
