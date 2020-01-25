package core.factory;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import core.page.Page;
import core.scanner.PageScanner;
import core.util.ReflectionUtils;
import core.util.StringUtil;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class PageCreator {

    private final WebDriver driver;

    private String baseUrl;
    private Map<Class<? extends AbstractPage>, Page> cache;

    public PageCreator(WebDriver driver) {
        this.driver = driver;
//        this.baseUrl = baseUrl;
//        this.cache = new HashMap<>();
    }

    public <T extends AbstractPage> T createPage(String pageName) {
        Class<T> page = (Class<T>) PageScanner.getPageByName(pageName);
        return createPage(page);
    }

    @SneakyThrows
    public <T extends AbstractPage> T createPage(Class<T> type) {
        try {
//            Page page = cache.get(type);
//            if (page != null) {
//                return type.cast(page);
//            }

            PageAccessor[] pageAnnotations = type.getDeclaredAnnotationsByType(PageAccessor.class);
            if (pageAnnotations.length == 0) {
                throw new Exception("Class of type [" + type + "] is not a page object.\r\nMissing annottion " + "PageAccessor annotation");
            }

            PageAccessor pageAccessor = pageAnnotations[0];
//            Configuration configuration = new Configuration();
//            String baseUrl2 = configuration.getBaseUrl();
//            String baseUrl1 = configuration.getBaseUrl();
            String baseUrl = "https://qa.academy.wgu.edu";
            String url = baseUrl + StringUtil.addSlash(pageAccessor.url());
            T t = ReflectionUtils.newInstance(type, this.driver, url, pageAccessor.name());
//            createContent(this.driver, t, null);
//            this.cache.put(t.getClass(), t);
            return t;
        } catch (Exception e) {
            throw new Exception("Failed to create page of type [" + type.getSimpleName() + "]", e);
        }
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void clearPagesCash() {
        cache.clear();
    }

    public <T extends AbstractPage> T getPage(Class<T> type) throws Exception {
        return createPage(type);
    }

    public <T extends AbstractPage> T getPage(String name) {
        return createPage(name);
    }

}
