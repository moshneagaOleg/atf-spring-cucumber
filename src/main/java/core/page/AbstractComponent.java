package core.page;

import core.element.YandexElement;
import org.openqa.selenium.WebDriver;

public abstract class AbstractComponent extends YandexElement {

    private WebDriver browser;

    // FIXME: 2/2/2020 create constructor for reflection get element

//    public AbstractComponent(WebDriver driver) {
//        super(driver);
//    }

//    public AbstractComponent(WebDriver driver) {
//        this.browser = driver;
//        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
//    }
//
//    public <T extends AbstractComponent> T initOnDemand() {
//        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(browser)), this);
//        return (T) this;
//    }

}
