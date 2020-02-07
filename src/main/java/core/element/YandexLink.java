package core.element;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.element.Image;

public class YandexLink extends Image {

    private Logger logger = LoggerFactory.getLogger(YandexLink.class.getSimpleName());

    public YandexLink(WebElement wrappedElement) {
        super(wrappedElement);
    }

    private String xPath;

    // FIXME: 2/2/2020 override image
    public <T extends YandexLink> T resolveLocator(String... args) {
        return resolveLocator(null, args);
    }

    // FIXME: 2/7/2020 make single parent Element with all methods and use it for all child classes
    private <T extends YandexLink> T resolveLocator(Class<T> clazz, String... args) {
        try {
            Object proxyOrigin = FieldUtils.readField(getWrappedElement(), "h", true);
            Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
            Object findBy = FieldUtils.readField(locator, "by", true);
            if (StringUtils.isBlank(xPath)) {
                xPath = FieldUtils.readField(findBy, "xpathExpression", true).toString();
            }
            String xpathResolved = String.format(xPath, args);
            FieldUtils.writeField(findBy, "xpathExpression", xpathResolved, true);
            if (clazz != null) {
                return clazz.cast(this);
            }
            return (T) this;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
