package core.factory;

import core.annotations.PageAccessor;
import core.exceptions.VTFException;
import core.page.AbstractPage;
import core.page.Page;
import core.util.ReflectionUtils;
import core.util.StringUtil;
import lombok.Setter;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;

import java.util.Map;

@Setter
public class PageCreator {

    public String baseUrl;
    private final WebDriver driver;
    private Map<Class<? extends AbstractPage>, Page> cache;

    public PageCreator(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
    }

    public <T extends AbstractPage> T createPage(String pageName) {
        Class<T> page = (Class<T>) PageScanner.getPageByName(pageName);
        return createPage(page);
    }

    @SneakyThrows
    public <T extends AbstractPage> T createPage(Class<T> type) {
        PageAccessor[] pageAnnotations = type.getDeclaredAnnotationsByType(PageAccessor.class);
        if (pageAnnotations.length == 0) {
            throw new VTFException("Class of type [" + type + "] is not a page object.\r\nMissing annottion " + "PageAccessor annotation");
        }
        PageAccessor pageAccessor = pageAnnotations[0];
        String url = baseUrl + StringUtil.addSlash(pageAccessor.url());
        return ReflectionUtils.newInstance(type, this.driver, url, pageAccessor.name());
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void clearPagesCash() {
        cache.clear();
    }

    public <T extends AbstractPage> T getPage(Class<T> type) {
        return createPage(type);
    }

    public <T extends AbstractPage> T getPage(String name) {
        return createPage(name);
    }

}
