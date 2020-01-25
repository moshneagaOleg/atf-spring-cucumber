package core.scanner;

import core.annotations.PageAccessor;
import core.element.YandexElement;
import core.page.AbstractPage;
import io.tpd.springbootcucumber.Config;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.lang.reflect.Field;
import java.util.Set;

public class PageScanner {

//    @Autowired
//    private static Environment environment;

    /**
     * Get element from page object by 'element name' and 'page name' using annotation @Name
     *
     * @param driver      WebDriver
     * @param elementName String Ex: close button, it annotated in page object
     * @param pageName    String Ex: Home, it annotated above page object
     * @return YandexElement
     */
    @SneakyThrows
    public static YandexElement getElementByName(WebDriver driver, String elementName, String pageName) {
        Reflections reflections = new Reflections(String.format("pageObject.%s", Config.TENANT));
        Set<Class<? extends AbstractPage>> classes = reflections.getSubTypesOf(AbstractPage.class);
        for (Class<? extends AbstractPage> pageObject : classes) {

            if (StringUtils.equals(pageObject.getAnnotation(PageAccessor.class).name(), pageName)) {

                for (Field field : pageObject.getDeclaredFields()) {
                    field.setAccessible(true);

                    if (StringUtils.equalsIgnoreCase(field.getAnnotation(Name.class).value(), elementName)) {
                        Object page = pageObject.getConstructor(WebDriver.class).newInstance(driver);

                        return (YandexElement) field.get(page);
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

//    /**
//     * @return ex: wgu, csu, ftk ...
//     */
//    private static String getActiveApplication() {
//        return Arrays.toString(environment.getActiveProfiles()).toLowerCase();
//    }

}
