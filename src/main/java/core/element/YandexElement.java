package core.element;

import core.assertation.VTFAssert;
import core.util.WaitUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static core.util.JSUtils.executeJavaScript;
import static core.util.ScreenshotUtils.highlightElement;
import static core.util.ScreenshotUtils.unhighlightElement;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class YandexElement extends HtmlElement {

    private static final String SCROLL_TO_ELEMENT_INTO_MIDDLE = "var viewPortHeight = " +
            "Math.max(document.documentElement.clientHeight, window.innerHeight || 0);" +
            "var elementTop = arguments[0].getBoundingClientRect().top;" +
            "window.scrollBy(0, elementTop-(viewPortHeight/2));";
    private static final String IS_ELEMENT_VISIBLE = " var elem = arguments[0], " +
            " box = elem.getBoundingClientRect(),     " +
            " cx = box.left + box.width / 2,          " +
            " cy = box.top + box.height / 2,          " +
            " e = document.elementFromPoint(cx, cy);  " +
            " for (; e; e = e.parentElement){       " +
            "   if (e === elem)                     " +
            "       return true;                    " +
            " }                                     " +
            " return false; ";

    private String xPath;
    private static final int ATTEMPTS_NUMBER = 3;
    private Logger logger = LoggerFactory.getLogger(YandexElement.class.getSimpleName());

//    /**
//     * Specifies wrapped {@link WebElement}.
//     *
//     * @param wrappedElement {@code WebElement} to wrap.
//     */
//    protected YandexElement(WebElement wrappedElement) {
//        super(wrappedElement);
//    }

    // FIXME: 2/2/2020 uncomment constructor
//    public YandexElement(WebElement wrappedElement) {
//        super(wrappedElement);
//    }

//    public YandexElement (WebDriver driver) {
//        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
//    }

//    public YandexElement(WebElement wrappedElement, String name) {
//        super(wrappedElement);
//        setName(name);
//    }

    @Override
    public void click() {
        scrollTo();
        waitForClickable(this, 10);
        execute(() -> this.getWrappedElement().click());
        logger.info("Clicked on '{}'", getName());
    }

    protected void execute(Runnable action) {
        highlightElement(getDriver(), this);
        executeWithAttempts(action);
        unhighlightElement(getDriver(), this);
    }

    protected <T> T execute(Supplier<T> action) {
        highlightElement(getDriver(), this);
        T result = executeWithAttempts(action);
        unhighlightElement(getDriver(), this);
        return result;
    }

    private void executeWithAttempts(Runnable action) {
        for (int i = 0; i < ATTEMPTS_NUMBER; i++) {
            try {
                action.run();
                break;
            } catch (StaleElementReferenceException | InvalidElementStateException ex) {
                logger.error("Runnable action execution failed on attempt " + i, ex);
            }
        }
        logger.info("Action on '{}' had performed", getName());
    }

    private <T> T executeWithAttempts(Supplier<T> action) {
        for (int i = 0; i < ATTEMPTS_NUMBER; i++) {
            try {
                return action.get();
            } catch (StaleElementReferenceException | InvalidElementStateException ex) {
                logger.error("Supplier action execution failed on attempt" + i, ex);
            }
        }
        logger.info("Action on '{}' had performed", getName());

        return null;
    }

    public void hover() {
        highlightElement(getDriver(), this);
        new Actions(getDriver()).moveToElement(getWrappedElement()).build().perform();
        unhighlightElement(getDriver(), this);
        WaitUtils.waitForRetry(300);
    }

    public void clickAndSwitchToNewWindow() {
        String openedHandle = getDriver().getWindowHandle();
        this.click();
        for (String handle : getDriver().getWindowHandles()) {
            if (!StringUtils.equals(openedHandle, handle)) {
                logger.info("Switch to new opened window");
                getDriver().switchTo().window(handle);
            }
        }
    }

    public void checkIfEquals(String value) {
        VTFAssert.assertThat(String.format("Validate '%s' text if equals", getName()), this.getText(), is(value));
    }

    public void checkIfMatches(String regexp) {
        VTFAssert.assertThat(String.format("Validate '%s' text if matches pattern '%s'", this.getText(), regexp),
                Pattern.matches(regexp, this.getText()));
    }

    public void checkIfEquals(Function<YandexElement, String> function, String value) {
        VTFAssert.assertThat(String.format("Validate '%s' text if equals", getName()),
                function.apply(this).trim(), is(value));
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

    @Override
    public String getText() {
        return super.getText().trim();
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
    public boolean isDisplayed() {
        return execute(() -> this.getWrappedElement().isDisplayed());
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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

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

    public void scrollTo() {
        executeJavaScript(getDriver(), SCROLL_TO_ELEMENT_INTO_MIDDLE, this.getWrappedElement());
    }

    public void scrollTo(WebElement element) {
        executeJavaScript(getDriver(), SCROLL_TO_ELEMENT_INTO_MIDDLE, element);
    }

    /**
     * @return Current webDriver
     */
    public WebDriver getDriver() {
        try {
            Object proxyOrigin = FieldUtils.readField(getWrappedElement(), "h", true);
            Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
            Object searchContext = FieldUtils.readField(locator, "searchContext", true);
            Object converter = FieldUtils.readField(searchContext, "converter", true);
            Object driver = FieldUtils.readField(converter, "driver", true);
            if (driver instanceof WebDriver) {
                return (WebDriver) driver;
            }
        } catch (IllegalArgumentException e) {
            try {
                Object browserSpecificDriver = FieldUtils.readField(getWrappedElement(), "parent", true);
                if (browserSpecificDriver instanceof WebDriver) {
                    return (WebDriver) browserSpecificDriver;
                }
            } catch (Exception e2) {
                Assert.fail("Can't get active driver" + e2.getMessage());
            }

        } catch (Exception e3) {
            Assert.fail("Can't get active driver" + e3.getMessage());
        }
        return null;
    }

    private By.ByXPath getLocator() {
        try {
            Object proxyOrigin = FieldUtils.readField(getWrappedElement(), "h", true);
            Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
            Object findBy = FieldUtils.readField(locator, "by", true);
            if (findBy != null) {
                return (By.ByXPath) findBy;
            }
        } catch (IllegalArgumentException ex) {
            try {
                String xpath = FieldUtils.readField(getWrappedElement(), "foundBy", true).toString().split("xpath:")[1].trim();
                return new By.ByXPath(xpath);
            } catch (IllegalAccessException e) {
                logger.warn(e.toString());
            }
        } catch (IllegalAccessException e) {
            logger.warn(e.toString());
        }
        return null;
    }

    public <T extends YandexElement> T setLocator(String newXpath) {
        try {
            Object proxyOrigin = FieldUtils.readField(getWrappedElement(), "h", true);
            Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
            Object findBy = FieldUtils.readField(locator, "by", true);

            FieldUtils.writeField(findBy, "xpathExpression", newXpath, true);
            return (T) this;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends YandexElement> T resolveLocator(String... args) {
        return resolveLocator(null, args);
    }

    private <T extends YandexElement> T resolveLocator(Class<T> clazz, String... args) {
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

    // FIXME: 2/4/2020 optimization
    private void waitUntilPageLoaded(int seconds) {
        ExpectedCondition<Boolean> expectation = driver -> executeJavaScript(getDriver(), "return document.readyState")
                .toString().equals("complete");
        try {
            WebDriverWait wait = new WebDriverWait(Objects.requireNonNull(getDriver()), seconds);
            wait.pollingEvery(Duration.of(1, ChronoUnit.SECONDS)).until(expectation);
        } catch (Exception error) {
            logger.warn("Timeout waiting for Page Load Request to Complete.", error);
        }
    }

    // FIXME: 2/4/2020 remove
    public <T extends YandexElement> T waitFor(T element, int seconds) {
        new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.visibilityOf(element));
        element.isDisplayedAssertion();
        return element;
    }

    // FIXME: 2/4/2020 remove
    public <T extends YandexElement> T waitForClickable(T element, int seconds) {
        new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.elementToBeClickable(element));
        element.isDisplayedAssertion();
        return element;
    }

}
