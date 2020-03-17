package io.tpd.springbootcucumber.core.element;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class WebSelect extends WebTypifiedElement {

    public WebSelect(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public String selectRandom(int startIndex) {
        log.info("Select '{}' with random option", getName());
        List<WebElement> options = getOptions();
        SecureRandom random = new SecureRandom();
        int index = random.nextInt(options.size() - startIndex) + startIndex;
        WebElement option = options.get(index);
        String value = option.getText();
        selectByVisibleText(value);

        return value;
    }

    private Select getSelect() {
        return new Select(this.getWrappedElement());
    }

    public void selectByVisibleText(String text) {
        //TODO log message
        this.getSelect().selectByVisibleText(text);
    }

    public void selectByVisibleTextIgnoreCase(String text) {
        // try to find the option via XPATH ...
        String ignoreSpace = ".//option[normalize-space(translate(., "
                + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')) =  ";
        List<WebElement> options = getWrappedElement().findElements(
                By.xpath(ignoreSpace + StringUtils.lowerCase(Quotes.escape(text)) + "]"));

        for (WebElement option : options) {
            try {
                Method setSelectedMethod = Select.class.getDeclaredMethod("setSelected",
                        WebElement.class, boolean.class);
                setSelectedMethod.setAccessible(true);
                Select selectInstance = this.getSelect();
                setSelectedMethod.invoke(selectInstance, option, true);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (!this.getSelect().isMultiple()) {
            return;
        }
    }


    public boolean isMultiple() {
        return this.getSelect().isMultiple();
    }

    public List<WebElement> getOptions() {
        return this.getSelect().getOptions();
    }

    public List<String> getOptionsAsStrings() {
        return getSelect().getOptions().stream()
                .map(we -> we.getText().trim())
                .sorted()
                .collect(Collectors.toList());
    }

    public List<WebElement> getAllSelectedOptions() {
        return this.getSelect().getAllSelectedOptions();
    }

    public WebElement getFirstSelectedOption() {
        return this.getSelect().getFirstSelectedOption();
    }

    public boolean hasSelectedOption() {
        return this.getOptions().stream().anyMatch(WebElement::isSelected);
    }

    public void selectByIndex(int index) {
        this.getSelect().selectByIndex(index);
    }

    public void selectByValue(String value) {
        this.getSelect().selectByValue(value);
    }

    public void deselectAll() {
        this.getSelect().deselectAll();
    }

    public void deselectByValue(String value) {
        this.getSelect().deselectByValue(value);
    }

    public void deselectByIndex(int index) {
        this.getSelect().deselectByIndex(index);
    }

    public void deselectByVisibleText(String text) {
        this.getSelect().deselectByVisibleText(text);
    }

    public void checkPlaceholder(String value) {
        // FIXME: 3/16/2020 add body
    }
}
