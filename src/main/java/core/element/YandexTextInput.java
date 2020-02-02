package core.element;

import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class YandexTextInput extends TextInput {

    public YandexTextInput(WebElement wrappedElement) {
        super(wrappedElement);
    }

    // FIXME: 2/2/2020 override all methods for input
}
