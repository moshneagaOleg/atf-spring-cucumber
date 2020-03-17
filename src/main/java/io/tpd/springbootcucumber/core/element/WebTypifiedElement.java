package io.tpd.springbootcucumber.core.element;

import io.tpd.springbootcucumber.core.assertation.VTFAssert;
import io.tpd.springbootcucumber.core.util.WaitUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static io.tpd.springbootcucumber.Config.SMALL_WAIT_TIMEOUT;
import static io.tpd.springbootcucumber.core.util.JSUtils.executeJavaScript;
import static io.tpd.springbootcucumber.core.util.ScreenshotUtils.highlightElement;
import static io.tpd.springbootcucumber.core.util.ScreenshotUtils.unhighlightElement;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

@Slf4j
public class WebTypifiedElement extends TypifiedElement {

    private static final String SCROLL_TO_ELEMENT_INTO_MIDDLE = "var viewPortHeight = "
            + "Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
            + "var elementTop = arguments[0].getBoundingClientRect().top;"
            + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
    private static final String IS_ELEMENT_VISIBLE = " var elem = arguments[0], "
            + " box = elem.getBoundingClientRect(),     "
            + " cx = box.left + box.width / 2,          "
            + " cy = box.top + box.height / 2,          "
            + " e = document.elementFromPoint(cx, cy);  "
            + " for (; e; e = e.parentElement){       "
            + "   if (e === elem)                     "
            + "       return true;                    "
            + " }                                     "
            + " return false; ";
    private static final int ATTEMPTS_NUMBER = 3;
    private String xPath;

    public WebTypifiedElement(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public WebTypifiedElement(WebElement wrappedElement, String name) {
        super(wrappedElement);
        setName(name);
    }

    @Override
    public void click() {
        // FIXME: 2/8/2020 add wait for click
        scrollTo(this);
        execute(() -> this.getWrappedElement().click());
        log.info("Clicked on '{}'", getName());
    }

    @Override
    public void submit() {
        execute(() -> this.getWrappedElement().submit());
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        execute(() -> this.getWrappedElement().sendKeys(keysToSend));
    }

    @Override
    public void clear() {
        execute(() -> this.getWrappedElement().clear());
    }

    @Override
    public boolean isSelected() {
        return execute(() -> this.getWrappedElement().isSelected());
    }

    @Override
    public boolean isEnabled() {
        return execute(() -> this.getWrappedElement().isEnabled());
    }

    @Override
    public String getText() {
        return super.getText().trim();
    }

    @Override
    public boolean isDisplayed() {
        return execute(() -> this.getWrappedElement().isDisplayed());
    }

    // FIXME: 2/8/2020 uncomment
    protected void execute(Runnable action) {
//        highlightElement(getDriver(), this);
        executeWithAttempts(action);
//        unhighlightElement(getDriver(), this);
    }

    // FIXME: 2/8/2020 uncomment
    protected <T> T execute(Supplier<T> action) {
//        highlightElement(getDriver(), this);
        T result = executeWithAttempts(action);
//        unhighlightElement(getDriver(), this);
        return result;
    }

    private void executeWithAttempts(Runnable action) {
        for (int i = 0; i < ATTEMPTS_NUMBER; i++) {
            try {
                action.run();
                break;
            } catch (StaleElementReferenceException | InvalidElementStateException ex) {
                log.error("Runnable action execution failed on attempt " + i, ex);
            }
        }
        log.info("Action on '{}' had performed", getName());
    }

    private <T> T executeWithAttempts(Supplier<T> action) {
        for (int i = 0; i < ATTEMPTS_NUMBER; i++) {
            try {
                return action.get();
            } catch (StaleElementReferenceException | InvalidElementStateException ex) {
                log.error("Supplier action execution failed on attempt" + i, ex);
            }
        }
        log.info("Action on '{}' had performed", getName());

        return null;
    }

    public void hover() {
        final int timeout = 300;
        highlightElement(getDriver(), this);
        new Actions(getDriver()).moveToElement(getWrappedElement()).build().perform();
        unhighlightElement(getDriver(), this);
        WaitUtils.waitForRetry(timeout);
    }

    public void clickAndSwitchToNewWindow() {
        String openedHandle = getDriver().getWindowHandle();
        this.click();
        for (String handle : getDriver().getWindowHandles()) {
            if (!StringUtils.equals(openedHandle, handle)) {
                log.info("Switch to new opened window");
                getDriver().switchTo().window(handle);
            }
        }
    }

    public void checkIfEquals(String value) {
        VTFAssert.assertThat(String.format("Validate '%s' text if equals", getName()), this.getText(), is(value));
    }

    public void checkIfEquals(Function<WebTypifiedElement, String> function, String value) {
        VTFAssert.assertThat(String.format("Validate '%s' text if equals", getName()),
                function.apply(this).trim(), is(value));
    }

    public void checkIfMatches(String regexp) {
        VTFAssert.assertThat(String.format("Validate '%s' text if matches pattern '%s'", this.getText(), regexp),
                Pattern.matches(regexp, this.getText()));
    }

    public void checkIfContains(String value) {
        VTFAssert.assertContains(this.getText(), value, String.format("Validate '%s' text if contained", getName()));
    }

    public void checkAttribute(String attributeName, String attributeValue) {
        VTFAssert.assertContains(getAttribute(attributeName), attributeValue,
                String.format("Validate '%s' element attribute '%s' contains value '%s'", getName(), attributeName, attributeValue));
    }

    public void isDisplayedAssertion() {
        try {
            VTFAssert.assertThat(String.format("Check element '%s' is displayed", getName()), isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail(String.format("Element '%s' does not exist", getName()));
        }
    }

    public void isVisibleAssertion() {
        VTFAssert.assertThat(String.format("Check element '%s' is visible", getName()), is(isDisplayed() && isVisible()));
    }

    public void isEnabledAssertion() {
        VTFAssert.assertThat(String.format("Check element '%s' is enabled", getName()), is(isEnabled()));
    }

    public void isNotEnabledAssertion() {
        VTFAssert.assertThat(String.format("Check element '%s' is not enabled", getName()), not(isEnabled()));
    }

    public void isNotDisplayedAssertion() {
        if (!isPresent()) {
            VTFAssert.assertThat(String.format("Check element '%s' is not present", getName()), true);
            return;
        }
        VTFAssert.assertThat(String.format("Check element '%s' is not displayed", getName()), !isDisplayed());
    }

    public void isNotVisibleAssertion() {
        VTFAssert.assertThat(String.format("Check element '%s' is not visible", getName()), not(isVisible()));
    }

    /**
     * This method checks if the elements exists in DOM instantly, no time-out or recursive checking.<br>
     * This functionality is useful for negative webElement scenarios in particular, when we check element is not present.<br>
     * Method will also check not more then 1 element is located in DOM, otherwise exception is thrown.
     *
     * @return <p><b>true</b> - element is present</p><p><b>false</b> - element not present in DOM</p><p><b>null</b> - exception</p>
     */
    public Boolean isPresent() {
        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        int numberOfElements = driver.findElements(getLocator()).size();
        driver.manage().timeouts().implicitlyWait(SMALL_WAIT_TIMEOUT, TimeUnit.SECONDS);

        return numberOfElements >= 1;
    }

    /**
     * This method checks if element is visible on your browser screen.
     * <p><b>false</b>  ~ If Element is present in DOM, but not visible on your screen
     * <p><b>true</b>   ~ If Element is present Both in DOM and on your browser's visible area.
     */
    public Boolean isVisible() {
        return executeJavaScript(getDriver(), IS_ELEMENT_VISIBLE, this);
    }

    public void scrollTo(WebElement element) {
        executeJavaScript(getDriver(), SCROLL_TO_ELEMENT_INTO_MIDDLE, element);
    }

    /**
     * @return Current webDriver
     */
    @SneakyThrows
    public WebDriver getDriver() {
        // TODO 11/02/2020: this method becomes messy. To be rewritten or use Browser component in future.
        WebElement wrappedElement = getWrappedElement();
        try {
            Object proxyOrigin = FieldUtils.readField(wrappedElement, "h", true);
            Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
            Object searchContext = FieldUtils.readField(locator, "searchContext", true);
            Object h = FieldUtils.readField(searchContext, "h", true);
            Object locatorInner = FieldUtils.readField(h, "locator", true);
            Object searchContextInner = FieldUtils.readField(locatorInner, "searchContext", true);
            Object locationContext = FieldUtils.readField(searchContextInner, "locationContext", true);
            Object executeMethod = FieldUtils.readField(locationContext, "executeMethod", true);
            return (WebDriver) FieldUtils.readField(executeMethod, "driver", true);
        } catch (IllegalArgumentException e) {
            Object proxyOrigin = FieldUtils.readField(wrappedElement, "h", true);
            Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
            Object searchContext = FieldUtils.readField(locator, "searchContext", true);
            Object locationContext = FieldUtils.readField(searchContext, "locationContext", true);
            Object executeMethod = FieldUtils.readField(locationContext, "executeMethod", true);
            return (WebDriver) FieldUtils.readField(executeMethod, "driver", true);
        }
    }

    public By.ByXPath getLocator() {
        try {
            Object proxyOrigin = FieldUtils.readField(getWrappedElement(), "h", true);
            Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
            Object findBy = FieldUtils.readField(locator, "by", true);
            if (findBy != null) {
                return (By.ByXPath) findBy;
            }
        } catch (IllegalArgumentException ex) {
            try {
                String xpath = FieldUtils.readField(getWrappedElement(), "foundBy", true)
                        .toString().split("xpath:")[1].trim();
                return new By.ByXPath(xpath);
            } catch (IllegalAccessException e) {
                log.warn(e.toString());
            }
        } catch (IllegalAccessException e) {
            log.warn(e.toString());
        }
        return null;
    }

    public <T extends WebTypifiedElement> T setLocator(String newXpath) {
        try {
            Object proxyOrigin = FieldUtils.readField(getWrappedElement(), "h", true);
            Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
            Object findBy = FieldUtils.readField(locator, "by", true);

            FieldUtils.writeField(findBy, "xpathExpression", newXpath, true);
            return (T) this;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends WebTypifiedElement> T resolveLocator(String... args) {
        return resolveLocator(null, args);
    }

    public <T extends WebTypifiedElement> T resolveLocator(Class<T> clazz, String... args) {
        try {
            Object proxyOrigin = FieldUtils.readField(getWrappedElement(), "h", true);
            Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
            Object findBy = FieldUtils.readField(locator, "by", true);
            if (StringUtils.isBlank(xPath)) {
                xPath = FieldUtils.readField(findBy, "xpathExpression", true).toString();
            }
            String xpathResolved = String.format(xPath, args);
            FieldUtils.writeField(findBy, "xpathExpression", xpathResolved, true);
            if (clazz != null) {
                return clazz.cast(this);
            }
            return (T) this;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
