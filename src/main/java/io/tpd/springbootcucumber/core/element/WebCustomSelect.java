package io.tpd.springbootcucumber.core.element;

import io.tpd.springbootcucumber.core.exceptions.SLException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;

import java.security.SecureRandom;
import java.util.List;
import java.util.function.Supplier;

import static io.tpd.springbootcucumber.Config.SMALL_WAIT_TIMEOUT;
import static io.tpd.springbootcucumber.core.assertation.VTFAssert.assertThat;
import static io.tpd.springbootcucumber.core.util.WaitUtils.waitUntilCondition;
import static java.lang.String.format;
import static org.hamcrest.Matchers.*;

@Slf4j
public class WebCustomSelect extends WebTypifiedElement {

    public WebCustomSelect(WebElement wrappedElement) {
        super(wrappedElement);
    }

    private Supplier<Boolean> selectIsOpened = () -> {
        if (!getOptions().get(0).isDisplayed()) {
            click();
            return false;
        }
        return true;
    };

    public List<WebElement> getOptions() {
        return getWrappedElement().findElements(By.xpath("..//following-sibling::ul/li"));
    }

    public void shouldNotHave(String option) {
        waitUntilCondition(this::isPresent, true, SMALL_WAIT_TIMEOUT);
        List<WebElement> options = getOptions();
        click();
        Object[] innerTexts = options.stream().map(element -> element.getAttribute("innerText")).toArray();
        assertThat("The searched value should not be present in dropdown",
                innerTexts, not(arrayContaining(option)));
    }

    public void selectByVisibleText(String value) {
        waitUntilCondition(this::isPresent, true, SMALL_WAIT_TIMEOUT);
        String initValue = getText().trim();
        log.info("Select '{}' element with option '{}'", getName(), value);
        assertThat(format("required option for '%s' is not empty", getName()),
                value, not(emptyOrNullString()));
        assertThat(format("element '%s' contains options", getName()),
                getOptions().size(), greaterThan(0));
        waitUntilCondition(selectIsOpened, Matchers.is(true), SMALL_WAIT_TIMEOUT);
        for (WebElement element : getOptions()) {
            if (StringUtils.equals(value, element.getText().trim())) {
                element.click();
                break;
            }
        }
        if (StringUtils.equals(getText().trim(), initValue) && !StringUtils.equals(initValue, value)) {
            throw new SLException(format("Value: '%s' was not selected", value));
        }
        assertThat("Option was selected correctly", getText().trim(), equalTo(value));
    }

    public String selectRandom(int startIndex) {
        waitUntilCondition(this::isPresent, true, SMALL_WAIT_TIMEOUT);
        String initValue = getText().trim();
        log.info("Select '{}' with random option", getName());
        click();
        if (!getOptions().get(0).isDisplayed()) {
            click();
        }
        List<WebElement> options = getOptions();
        assertThat("More than 0 options to select → drop down is not empty", options.size(),
                greaterThan(0));
        SecureRandom random = new SecureRandom();
        int index;
        String valueToBeSelected = null;
        WebElement option = null;
        while (StringUtils.isBlank(valueToBeSelected)) {
            index = random.nextInt(options.size() - startIndex) + startIndex;
            option = options.get(index);
            valueToBeSelected = option.getText();
            log.info("One more attempt to select value");
        }
        waitUntilCondition(selectIsOpened, is(true), SMALL_WAIT_TIMEOUT);
        log.info("Try to select '{}'", valueToBeSelected);
        scrollTo(option);
        try {
            option.click();
        } catch (ElementClickInterceptedException e) {
            selectByVisibleText(valueToBeSelected);
        }

        if (StringUtils.equals(initValue, valueToBeSelected)) {
            throw new SLException(format("Value: '%s' was not selected", valueToBeSelected));
        }
        assertThat("Option was selected right", getText().trim(), equalTo(valueToBeSelected));
        return valueToBeSelected;
    }

    public String getPlaceholder() {
        return findElement(By.xpath("./span")).getText().trim();
    }

}
