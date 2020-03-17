package io.tpd.springbootcucumber.core.factory;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.element.WebTypifiedElement;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.lang.reflect.Field;
import java.util.Set;

public class PageScanner {

    /**
     * Get element from page object by 'element name' and 'page name' using annotation @Name
     *
     * @param driver      WebDriver
     * @param elementName String Ex: close button, it annotated in page object
     * @param pageName    String Ex: Home, it annotated above page object
     * @return WebTypifiedElement
     */
    @SneakyThrows
    public static WebTypifiedElement getElementByName(WebDriver driver, String elementName, String pageName) {
        Reflections reflections = new Reflections("io.tpd.springbootcucumber.pageObject");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(PageAccessor.class);
        for (Class<?> pageObject : classes) {
            if (pageObject.isAnnotationPresent(PageAccessor.class))
                if (StringUtils.equals(pageObject.getAnnotation(PageAccessor.class).name(), pageName))
                    for (Field field : pageObject.getDeclaredFields()) {
                        field.setAccessible(true);
                        if (field.isAnnotationPresent(Name.class)) {
                            if (StringUtils.equalsIgnoreCase(field.getAnnotation(Name.class).value(), elementName)) {
                                Object page = HtmlElementLoader.createPageObject(pageObject, driver);
                                return (WebTypifiedElement) field.get(page);
                            }
                        } else if (!field.isAnnotationPresent(Name.class)) {
                            for (Field componentField : field.getType().getFields()) {
                                componentField.setAccessible(true);
                                if (componentField.isAnnotationPresent(Name.class))
                                    if (StringUtils.equalsIgnoreCase(componentField.getAnnotation(Name.class).value(), elementName)) {
                                        return (WebTypifiedElement) componentField.get(HtmlElementLoader.create(field.getType(), driver));
                                    }
                            }
                        }
                    }
        }
        throw new RuntimeException(String.format("Error during getting element by elementName: '%s' is not found ", elementName));
    }

    /**
     * Get page by name from some package using tenant
     *
     * @param pageName String ex: getPageByName("Index")
     * @return Class AbstractPage
     */
    @SneakyThrows
    public static Class<? extends AbstractPage> getPageByName(String pageName) {
        Reflections reflections = new Reflections("io.tpd.springbootcucumber.pageObject");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(PageAccessor.class);
        for (Class<?> pageObject : classes) {
            if (StringUtils.equals(pageObject.getAnnotation(PageAccessor.class).name(), pageName))
                return (Class<? extends AbstractPage>) pageObject;
        }
        throw new RuntimeException(String.format("Error during getting page by pageName: '%s' is not found ", pageName));
    }

}
