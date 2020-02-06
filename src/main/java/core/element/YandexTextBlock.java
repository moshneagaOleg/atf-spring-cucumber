package core.element;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class YandexTextBlock extends TextBlock {

    private Logger logger = LoggerFactory.getLogger(YandexTextBlock.class.getSimpleName());

    public YandexTextBlock(WebElement wrappedElement) {
        super(wrappedElement);
    }

    // FIXME: 2/2/2020 override image

}
