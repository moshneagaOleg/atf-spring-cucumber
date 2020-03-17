package io.tpd.springbootcucumber.core.page;

import io.tpd.springbootcucumber.core.element.WebTextBlock;
import io.tpd.springbootcucumber.core.element.WebTypifiedElement;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.function.Supplier;

import static io.tpd.springbootcucumber.Config.BIG_WAIT_TIMEOUT;
import static io.tpd.springbootcucumber.core.assertation.VTFAssert.assertThat;
import static io.tpd.springbootcucumber.core.util.WaitUtils.waitUntilCondition;
import static java.lang.String.format;

public abstract class AbstractPage implements Page {

    @Name("Generic Page Title")
    @Timeout(BIG_WAIT_TIMEOUT)
    @FindBy(xpath = "//main//*[normalize-space() ='%s']")
    public WebTextBlock gnrcPageTitle;

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String url;
    protected String name;

    public AbstractPage(WebDriver driver, String url, String name) {
        this.driver = driver;
        this.url = url;
        this.name = name;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void open() {
        driver.navigate().to(url);
        isReady();
        assertThat(format("[%s] page has proper url", getName()),
                waitUntilCondition(this::isCurrentUrl, true, BIG_WAIT_TIMEOUT));
        validatePageTitle();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public boolean isCurrentUrl() {
        return StringUtils.equals(StringUtils.substringBefore(driver.getCurrentUrl(), "?"), url);
    }

    @Override
    @SneakyThrows
    public boolean isReady() {
        Supplier<Boolean> pageIsReady = () -> ((JavascriptExecutor) driver).executeScript("return document.readyState")
                .toString().equals("complete");
        return waitUntilCondition(pageIsReady, true, BIG_WAIT_TIMEOUT);
    }

    @Override
    public String toString() {
        return "AbstractPage{"
                + "url='" + url + '\''
                + ", name='" + name + '\''
                + '}';
    }

    public <T extends AbstractPage> T initOnDemand() {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
        isReady();
        wait = new WebDriverWait(driver, BIG_WAIT_TIMEOUT);
        return (T) this;
    }

    public abstract WebTypifiedElement getPageTitle();

    public void validatePageTitle() {
        assertThat("Validate page title",
                waitUntilCondition(() -> getPageTitle().isDisplayed(), true, 30));
    }

}
