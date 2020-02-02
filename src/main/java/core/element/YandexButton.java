package core.element;

import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Button;

public class YandexButton extends Button {

    public YandexButton(WebElement wrappedElement) {
        super(wrappedElement);
    }

    // FIXME: 2/2/2020 override click

}
