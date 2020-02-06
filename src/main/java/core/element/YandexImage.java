package core.element;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.element.Image;

public class YandexImage extends Image {

    private Logger logger = LoggerFactory.getLogger(YandexImage.class.getSimpleName());

    public YandexImage(WebElement wrappedElement) {
        super(wrappedElement);
    }

    // FIXME: 2/2/2020 override image

}
