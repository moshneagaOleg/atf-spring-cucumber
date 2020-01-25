package core.page;

import core.element.YandexElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class AbstractPage implements Page {
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

    public <T extends AbstractPage> T initOnDemand() {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
        isReady();
        wait = new WebDriverWait(driver, 30);

        return (T) this;
    }

    public <T extends YandexElement> T waitFor(T element, int seconds) {
        new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
        element.isDisplayedAssertion();
        return element;
    }

    @Override
    public boolean isReady() {
        try {
            ExpectedCondition<Boolean> expectation = driver ->
                    ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            WebDriverWait wait = new WebDriverWait(driver, 10);
//            wait.pollingEvery(Duration.of(1, ChronoUnit.SECONDS)).until(expectation);
            return true;
        } catch (Exception error) {
            error.printStackTrace();
        }
        return false;
    }


//    public <T extends YandexElement> T waitFor(T element, int seconds) {
//        new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
//        element.isDisplayedAssertion();
//        return element;
//    }

    public <T extends YandexElement> T waitForClickable(T element, int seconds) {
        new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(element));
        element.isDisplayedAssertion();
        return element;
    }

}
