package core.element;

import core.assertation.VTFAssert;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
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

    private String xPath;

    // FIXME: 2/2/2020 override image
    public <T extends YandexTextBlock> T resolveLocator(String... args) {
        return resolveLocator(null, args);
    }

    private <T extends YandexTextBlock> T resolveLocator(Class<T> clazz, String... args) {
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

    public void isDisplayedAssertion() {
        try {
            VTFAssert.assertThat(String.format("Check element '%s' is displayed", getName()), isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail(String.format("Element '%s' does not exist", getName()));
        }
    }

}
