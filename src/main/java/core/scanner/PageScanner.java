package core.scanner;

import core.annotations.PageAccessor;
import core.element.YandexButton;
import core.page.AbstractPage;
import io.tpd.springbootcucumber.Config;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.lang.reflect.Field;
import java.util.Set;

public class PageScanner {

    /**
     * Get element from page object by 'element name' and 'page name' using annotation @Name
     *
     * @param driver      WebDriver
     * @param elementName String Ex: close button, it annotated in page object
     * @param pageName    String Ex: Home, it annotated above page object
     * @return YandexElement
     */
    @SneakyThrows
    public static TypifiedElement getElementByName(WebDriver driver, String elementName, String pageName) {
        Reflections reflections = new Reflections(String.format("pageObject.%s", Config.TENANT));
        Set<Class<? extends AbstractPage>> classes = reflections.getSubTypesOf(AbstractPage.class);
        for (Class<? extends AbstractPage> pageObject : classes) {
            if (StringUtils.equals(pageObject.getAnnotation(PageAccessor.class).name(), pageName)) {
                for (Field field : pageObject.getDeclaredFields()) {
                    field.setAccessible(true);
                    /////////////////////////////// new //////////////////////////////////////
                    if (field.getAnnotation(Name.class) == null) {
                        for (Field componentField : field.getType().getFields()) {
                            componentField.setAccessible(true);
                            if (StringUtils.equalsIgnoreCase(componentField.getAnnotation(Name.class).value(), elementName)) {

                                // FIXME: 2/2/2020 resolve page with constructor
                                return (YandexButton) componentField.get(field.getType());
                            }
                        }
                    } else {
                        if (StringUtils.equalsIgnoreCase(field.getAnnotation(Name.class).value(), elementName)) {
                            Object page = pageObject.getConstructor(WebDriver.class).newInstance(driver);
                            // FIXME: 2/2/2020 return custom element
//                            return (YandexElement) field.get(page);
                        }
                    }
                }
            }
        }

        throw new RuntimeException(String.format("Error during getting element by elementName: '%s' is not found ", elementName));
    }

    @SneakyThrows
    public static Class<? extends AbstractPage> getPageByName(String pageName) {
        Reflections reflections = new Reflections(String.format("pageObject.%s", Config.TENANT));
        Set<Class<? extends AbstractPage>> classes = reflections.getSubTypesOf(AbstractPage.class);
        for (Class<? extends AbstractPage> pageObject : classes) {
            if (StringUtils.equals(pageObject.getAnnotation(PageAccessor.class).name(), pageName)) {
                return pageObject;
            }
        }
        throw new RuntimeException(String.format("Error during getting page by pageName: '%s' is not found ", pageName));
    }

}
