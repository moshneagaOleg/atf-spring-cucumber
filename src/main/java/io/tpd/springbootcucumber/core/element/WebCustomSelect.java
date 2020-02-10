package io.tpd.springbootcucumber.core.element;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;

import java.security.SecureRandom;
import java.util.List;

public class WebCustomSelect extends WebTypifiedElement {

    public WebCustomSelect(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public List<WebElement> getOptions() {
        return getWrappedElement().findElements(By.xpath("..//following-sibling::ul/li"));
    }

    // FIXME: 2/8/2020 uncomment
    public void shouldNotHave(String option) {
//        WaitUtils.waitUntilCondition(this::isPresent, true, 5);
        List<WebElement> options = getWrappedElement().findElements(By.xpath("//button/following-sibling::ul/li"));
        click();
        Object[] innerTexts = options.stream().map(element -> element.getAttribute("innerText")).toArray();
//        assertThat("The searched value should not be present in dropdown",
//                innerTexts, Matchers.not(Matchers.arrayContaining(option)));

    }

    // FIXME: 2/8/2020 uncomment
    public void selectByVisibleText(String value) {
//        WaitUtils.waitUntilCondition(this::isPresent, true, 5);
        String initValue = getText().trim();
//        log.info("Select '{}' with option '{}'", getName(), value);
//        loggingAssert.assertTrue(!StringUtils.isBlank(value), String.format("required option for '%s' is not empty", getName()));
//        loggingAssert.assertMoreThanZero(getOptions().size(), String.format("'%s' element contains options", getName()));
        click();
        if (!getOptions().get(0).isDisplayed()) {
            click();
        }
        for (WebElement element : getOptions()) {
            if (StringUtils.equals(value, element.getText().trim())) {
                element.click();
            }
        }
        if (StringUtils.equals(getText().trim(), initValue) && !StringUtils.equals(initValue, value)) {
//            throw new StraighterLineException(String.format("Value: '%s' was not selected", value));
        }
//        loggingAssert.assertEquals(getText().trim(), value, "Option was selected right");
    }

    // FIXME: 2/8/2020 uncomment
    public String selectRandom(int startIndex) {
//        WaitUtils.waitUntilCondition(this::isPresent, true, 5);
        String initValue = getText().trim();
//        log.info("Select '{}' with random option", getName());
        click();
        if (!getOptions().get(0).isDisplayed()) {
            click();
        }
        List<WebElement> options = getOptions();
//        loggingAssert.assertTrue(options.size() > 0, "More than 0 options to select");
        SecureRandom random = new SecureRandom();
        int index;
        String value = null;
        WebElement option = null;
        while (StringUtils.isBlank(value)) {
            index = random.nextInt(options.size() - startIndex) + startIndex;
            option = options.get(index);
            value = option.getText();
//            log.info("One more attempt to select value");
        }
        if (!getOptions().get(0).isDisplayed()) {
            click();
        }
//        log.info("Try to select '{}'", value);
//        Helpers.scrollToElement(CustomDriver.getInstance(), option);
//        Helpers.scrollToElement(getDriver(), option);

        try {
            option.click();
        } catch (ElementClickInterceptedException e) {
            selectByVisibleText(value);
        }

        if (StringUtils.equals(initValue, value)) {
//            throw new StraighterLineException(String.format("Value: '%s' was not selected", value));
        }
        return value;
    }

    public void checkCurrentValue(String value) {
//        loggingAssert.assertEquals(getPlaceholder(), value, String.format("Check current value for '%s'", getName()));
    }

    public String getPlaceholder() {
        return findElement(By.xpath("./span")).getText().trim();
    }

    public void verifyPlaceholder(String expectedValue, int timeout) {
//        Helpers.waitUntilCondition(() -> getPlaceholder().equals(expectedValue), true, timeout);
//        log.info(String.format("Check current value for '%s'", getName()));
    }

}