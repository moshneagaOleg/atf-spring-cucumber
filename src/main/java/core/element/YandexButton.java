package core.element;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.element.Button;

public class YandexButton extends Button {

    private Logger logger = LoggerFactory.getLogger(YandexButton.class.getSimpleName());

    public YandexButton(WebElement wrappedElement) {
        super(wrappedElement);
    }


    // FIXME: 2/7/2020 make single parent Element with all methods and use it for all child classes

    @Override
    public void click() {
        super.click();
        logger.info("Clicked on '{}'", getName());
    }

    // FIXME: 2/2/2020 override click

}
