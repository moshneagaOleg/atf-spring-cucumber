package io.tpd.springbootcucumber.core.factory;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.exceptions.VTFException;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import io.tpd.springbootcucumber.core.page.Page;
import io.tpd.springbootcucumber.core.util.ReflectionUtils;
import io.tpd.springbootcucumber.core.util.StringUtil;
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
        if (type.isAnnotationPresent(PageAccessor.class)) {
            PageAccessor pageAccessor = type.getAnnotation(PageAccessor.class);
            String fullUrl = baseUrl + StringUtil.addSlash(pageAccessor.url());
            return ReflectionUtils.newInstance(type, this.driver, fullUrl, pageAccessor.name());
        } else {
            throw new VTFException("Class of type [" + type + "] is not a page object.\r\nMissing annottion " + "PageAccessor annotation");
        }
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
