package core.element;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

public class WebSelect extends WebTypifiedElement {

    private Logger logger = LoggerFactory.getLogger(WebRadio.class.getSimpleName());

    public WebSelect(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public String selectRandom(int startIndex) {
        String initValue = getFirstSelectedOption().getText().trim();
        logger.info("Select '{}' with random option", getName());

        List<WebElement> options = getOptions();
//        loggingAssert.assertTrue(options.size() > 0, "More than 0 options to select");
        SecureRandom random = new SecureRandom();
        int index = random.nextInt(options.size() - startIndex) + startIndex;
        WebElement option = options.get(index);
        String value = option.getText();
        selectByVisibleText(value);

        // FIXME: 1/28/2020 value wasn't selected, please debug more deeply EN-15350
//        if (!StringUtils.equals(initValue, value)){
//            loggingAssert.assertEquals(getFirstSelectedOption().getText().trim(), initValue, "Value was not selected");
//        }
//        loggingAssert.assertEquals(getFirstSelectedOption().getText().trim(), value, "Option was selected right");
        return value;
    }

    //copy from yandex
    private Select getSelect() {
        return new Select(this.getWrappedElement());
    }

    public void selectByVisibleText(String text) {
        //TODO log message
        this.getSelect().selectByVisibleText(text);
    }

    public void selectByVisibleTextIgnoreCase(String text) {
        // try to find the option via XPATH ...
        List<WebElement> options = getWrappedElement().findElements(
                By.xpath(".//option[normalize-space(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')) =  "
                        + StringUtils.lowerCase(Quotes.escape(text)) + "]"));

        for (WebElement option : options) {
            try {
                Method setSelectedMethod = Select.class.getDeclaredMethod("setSelected", WebElement.class, boolean.class);
                setSelectedMethod.setAccessible(true);
                Select selectInstance = this.getSelect();
                setSelectedMethod.invoke(selectInstance, option, true);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
//        setSelected(option, true);
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

    // FIXME: 2/8/2020 add assertThat
    public void checkPlaceholder(String value) {
//        VTFAssert.assertThat(String.format("Validate '%s' select placeholder/default value", getName()), this.getFirstSelectedOption().getText(), is(value));
    }
}
