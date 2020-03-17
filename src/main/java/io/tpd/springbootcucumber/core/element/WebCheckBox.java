package io.tpd.springbootcucumber.core.element;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

@Slf4j
public class WebCheckBox extends WebTypifiedElement {

    private final int numberOfAttempts = 5;

    public WebCheckBox(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public WebCheckBox(WebElement wrappedElement, String name) {
        super(wrappedElement);
        setName(name);
    }

    public WebElement getLabel() {
        try {
            return this.getWrappedElement().findElement(By.xpath("following-sibling::label"));
        } catch (NoSuchElementException var2) {
            return null;
        }
    }

    public String getLabelText() {
        WebElement label = this.getLabel();
        return label == null ? null : label.getText();
    }

    public String getText() {
        return this.getLabelText();
    }

    public void select() {
        if (!this.isSelected()) {
            tryToClickWithAttempts(numberOfAttempts);
        }
        log.debug(String.format("Click on %s", getName()));
    }

    public void deselect() {
        if (this.isSelected()) {
            tryToClickWithAttempts(numberOfAttempts);
        }
    }

    private void tryToClick() {
        try {
            getWrappedElement().click();
        } catch (WebDriverException ex) {
            log.debug("Can't click on element, try to click on label");
            getLabel().click();
        }
    }

    private void tryToClickWithAttempts(int attempts) {
        // TODO: stable
    }

    public void set(boolean value) {
        if (value) {
            this.select();
        } else {
            this.deselect();
        }
    }

}
