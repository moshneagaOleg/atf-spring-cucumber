package core.element;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class WebRadio extends WebTypifiedElement {

    private Logger logger = LoggerFactory.getLogger(WebRadio.class.getSimpleName());

    public WebRadio(WebElement wrappedElement) {
        super(wrappedElement);
    }

    private List<WebElement> getLabels() {
        try {
            return getWrappedElement().findElements(By.xpath("//following-sibling::label"));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void selectByVisibleText(String text) {
        logger.info("Select button with text '{}' for {}", text, getName());
        WebElement matchingButton = getButtons()
                .stream()
                .filter(b -> text.equals(b.findElement(By.xpath("following-sibling::label")).getText()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Cannot locate label for radiobutton with text: %s", text)));
        if (!matchingButton.isSelected()) {
            try {
                matchingButton.click();
            } catch (WebDriverException ex) {
                logger.debug("Can't click on element, try to click on label");
                for (WebElement elm : getLabels()) {
                    if (StringUtils.equalsIgnoreCase(elm.getText().trim(), text)) {
                        elm.click();
                        return;
                    }
                }
            }
        }
    }

    public List<WebElement> getButtons() {
        String radioName = this.getWrappedElement().getAttribute("name");
        String xpath;
        if (radioName == null) {
            xpath = "self::* | following::input[@type = 'radio'] | preceding::input[@type = 'radio']";
        } else {
            xpath = String.format("self::* | following::input[@type = 'radio' and @name = '%s'] | preceding::input[@type = 'radio' and @name = '%s']", radioName, radioName);
        }

        return this.getWrappedElement().findElements(By.xpath(xpath));
    }

    public WebElement getSelectedButton() {
        return this.getButtons().stream().filter(WebElement::isSelected).findAny().orElseThrow(() ->
                new NoSuchElementException("No selected button")
        );
    }

    public boolean hasSelectedButton() {
        return this.getButtons().stream().anyMatch(WebElement::isSelected);
    }

    public void selectByValue(String value) {
        WebElement matchingButton = this.getButtons()
                .stream()
                .filter((b) -> value.equals(b.getAttribute("value")))
                .findFirst().orElseThrow(() -> new NoSuchElementException(String.format("Cannot locate radio button with value: %s", value)));
        this.selectButton(matchingButton);
    }

    public void selectByIndex(int index) {
        List<WebElement> buttons = this.getButtons();
        if (index >= 0 && index < buttons.size()) {
            this.selectButton(buttons.get(index));
        } else {
            throw new NoSuchElementException(String.format("Cannot locate radio button with index: %d", index));
        }
    }

    private void selectButton(WebElement button) {
        if (!button.isSelected()) {
            try {
                button.click();
            } catch (ElementClickInterceptedException ex) {
                logger.debug("Can't click on element, try to click on label");
            }
        }

    }

}
