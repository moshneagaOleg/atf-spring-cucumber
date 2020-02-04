package core.scanner;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import io.tpd.springbootcucumber.Config;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.reflections.Reflections;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.lang.reflect.Field;
import java.util.Set;

public class PageScanner {

    protected static ElementLocatorFactory factory;


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
            if (pageObject.isAnnotationPresent(PageAccessor.class))
                if (StringUtils.equals(pageObject.getAnnotation(PageAccessor.class).name(), pageName))
                    for (Field field : pageObject.getDeclaredFields()) {
                        field.setAccessible(true);
                        if (field.isAnnotationPresent(Name.class)) {
                            if (StringUtils.equalsIgnoreCase(field.getAnnotation(Name.class).value(), elementName)) {
                                Object page = HtmlElementLoader.createPageObject(pageObject, driver);
//                                return (YandexElement) field.get(page);
                            }
                        } else if (!field.isAnnotationPresent(Name.class)) {
                            for (Field componentField : field.getType().getFields()) {
                                componentField.setAccessible(true);
                                if (componentField.isAnnotationPresent(Name.class))
                                    if (StringUtils.equalsIgnoreCase(componentField.getAnnotation(Name.class).value(), elementName)) {
                                        // TODO: 2/3/2020 make html element
                                        String xpath = componentField.getAnnotation(FindBy.class).xpath();
                                        System.out.println();
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
            if (pageObject.isAnnotationPresent(PageAccessor.class))
                if (StringUtils.equals(pageObject.getAnnotation(PageAccessor.class).name(), pageName))
                    return pageObject;
        }
        throw new RuntimeException(String.format("Error during getting page by pageName: '%s' is not found ", pageName));
    }

//    public static WebElement decorateWebElement(ClassLoader loader, Field field) {
//        ElementLocator locator = factory.createLocator(field);
//        InvocationHandler handler = new WebElementNamedProxyHandler(locator, getElementName(field));
//
//        return createWebElementProxy(loader, handler);
//    }
//
//    public  static <T extends TypifiedElement> T decorateTypifiedElement(ClassLoader loader, Field field) {
//        WebElement elementToWrap = decorateWebElement(loader, field);
//
//        //noinspection unchecked
//        return createTypifiedElement((Class<T>) field.getType(), elementToWrap, getElementName(field));
//    }
//
//    public static <T extends TypifiedElement> T createTypifiedElement(Class<T> elementClass, WebElement elementToWrap,
//                                                                      String name) {
//        try {
//            T instance = newInstance(elementClass, elementToWrap);
//            instance.setName(name);
//            return instance;
//        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
//                | InvocationTargetException e) {
//            throw new HtmlElementsException(e);
//        }
//    }

}
