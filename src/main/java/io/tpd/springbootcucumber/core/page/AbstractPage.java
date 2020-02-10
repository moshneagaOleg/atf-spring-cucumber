package io.tpd.springbootcucumber.core.page;

import io.tpd.springbootcucumber.core.element.WebTypifiedElement;
import io.tpd.springbootcucumber.core.element.WebTextBlock;
import io.tpd.springbootcucumber.core.util.WaitUtils;
import lombok.SneakyThrows;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.function.Supplier;

public abstract class AbstractPage implements Page {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String url;
    protected String name;

    @Name("Generic Page Title")
    @Timeout(30)
    @FindBy(xpath = "//*[normalize-space()='%s']")
    public WebTextBlock gnrcPageTitle;

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
    public void open() {
        driver.navigate().to(url);
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
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public boolean isCurrentPage() {
        return driver.getCurrentUrl().startsWith(url);
    }

    @Override
    public String toString() {
        return "AbstractPage{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    @SneakyThrows
    public boolean isReady() {
        Supplier<Boolean> pageIsReady = ()-> ((JavascriptExecutor) driver).executeScript("return document.readyState")
                .toString().equals("complete");
        return WaitUtils.waitUntilCondition(pageIsReady, true, 30);
    }

    public <T extends AbstractPage> T initOnDemand() {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
        // FIXME: 2/4/2020 isReady a good way to stable test, but take more time ex: wgu.main().button get initOnDemand
        isReady();
        wait = new WebDriverWait(driver, 30);
        return (T) this;
    }

    public <T extends WebTypifiedElement> T waitFor(T element, int seconds) {
        new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
        element.isDisplayedAssertion();
        return element;
    }

    public <T extends WebTypifiedElement> T waitForClickable(T element, int seconds) {
        new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(element));
        element.isDisplayedAssertion();
        return element;
    }

}
