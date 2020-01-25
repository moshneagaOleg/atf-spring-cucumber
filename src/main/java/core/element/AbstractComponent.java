package core.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class AbstractComponent implements Component {

    private WebDriver browser;
    private Component parent;
    private String xpath;
    private String name;

    /**
     * Refers to the current instance of the parent class
     *
     * @param browser Browser we use
     * @param name    Element name
     * @param parent  The parent component of the element
     * @param xpath   xpath of the element
     */
    public AbstractComponent(WebDriver browser, String name, Component parent, String xpath) {
        this.parent = parent;
        this.xpath = xpath;
        this.name = name;
        this.browser = browser;
    }

    public String getLocator() {
        return xpath;
    }

    public Component getParent() {
        return parent;
    }

    public void setParent(Component parent) {
        this.parent = parent;
    }

    @Override
    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getFullXpath() {
        return parent != null ? parent.getFullXpath() + xpath : xpath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public WebDriver getBrowser() {
        return browser;
    }

    public void setBrowser(WebDriver browser) {
        this.browser = browser;
    }

    public YandexElement directFind() {
        return new YandexElement(browser.findElement(By.xpath(this.getFullXpath())));
    }

    public YandexElement find() {
        if (parent != null) {
            Wait<Component> wait = new FluentWait<>(parent).
                    withTimeout(30, TimeUnit.SECONDS).
                    pollingEvery(1, TimeUnit.SECONDS);
            wait.until(Component::isReady);
        }
        return new YandexElement(browser.findElement(By.xpath(this.getFullXpath())));
    }

    public YandexElement quickFind() {
        if (parent != null) {
            Wait<Component> wait = new FluentWait<>(parent).
                    withTimeout(4, TimeUnit.SECONDS).
                    pollingEvery(1, TimeUnit.SECONDS);
            wait.until(Component::isReady);
        }
        return new YandexElement(browser.findElement(By.xpath(this.getFullXpath())));
    }

    public List<WebElement> findAll() {
        return browser.findElements(By.xpath(this.getFullXpath()));
    }

    public String getText() {
        return find().getText();
    }

    public String getValue() {
        return find().getAttribute("value");
    }

    public String getAttributeValue(String attribute) {
        return find().getAttribute(attribute);
    }

    public boolean isSelected() {
        return find().isSelected();
    }

//    public boolean isDisplayed() {
//        return new IsDisplayedAction(this).execute();
//    }
//
//    public boolean notDisplayed() {
//        try {
//            return !quickFind().isDisplayed();
//        } catch (ElementNotFoundException | TimeoutException e) {
//            return true;
//        }
//    }

    public boolean isEnabled() {
        return find().isEnabled();
    }

    @Override
    public String toString() {
        return "AbstractComponent{" +
                "browser=" + browser +
                ", parent=" + parent +
                ", xpath='" + xpath + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
