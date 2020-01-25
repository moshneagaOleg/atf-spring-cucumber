package pageObject.wgu;

import core.element.AbstractComponent;
import core.element.Component;
import core.element.YandexElement;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class Header extends AbstractComponent implements Component {

//    @Timeout(30)
//    @Locator(xpath = "//img[@class='logo__img']", name = "Logo")
//    public YandexElement logoImg;

//    @FindBy(xpath = "//header/div/a[contains(@class,'logo')]")
//    public YandexElement logoHomeLnk;

//    @FindBy(xpath = "//div[contains(@class,'toasted toasted-primary success')]")
//    public YandexElement successMsg;

//    @FindBy(xpath = "//div[contains(@class,'toasted toasted-primary success')]")
//    public List<YandexElement> successMsgs;

//    @FindBy(xpath = "//div[contains(@class,'toasted toasted-primary error')]")
//    public List<YandexElement> errorMsgs;

//    @FindBy(xpath = "//div[contains(@class,'toasted toasted-primary error')]")
//    public YandexElement errorMsgTxt;

//    @FindBy(xpath = "//header//div[contains(@class,'photo-placeholder')]")
//    public YandexElement studentPhotoPlaceholderImg;

//    @FindBy(xpath = "//header//div[contains(@style,'background-image')]")
//    public YandexElement studentPhotoImg;

//    @FindBy(xpath = "//header//a[normalize-space()='Call: (888) 210-8880']")
//    public YandexElement callLnk;

//    @FindBy(xpath = "//a[@class='header-link' and normalize-space()='Live Chat']")
//    public YandexElement lifeChatLnk;

//    @Name("Help Center Link")
//    @Timeout(30)
//    @FindBy(xpath = "//a[@class='header-link'][contains(text(),'Help Center')]")
//    public YandexElement helpCenterLnk;

//    @FindBy(xpath = "//button[contains(@class,'hamburger')]")
//    public YandexElement hamMenuBtn;

//    @FindBy(xpath = "//div[contains(@class,'hamburger')]/div[contains(@class,'opened')]/ul")
//    public YandexElement hamMenuContainerElm;

//    @FindBy(xpath = "//div[contains(@class,'hamburger')]//span[normalize-space()='%s']")
//    public YandexElement hamMenuGenericLnk;

//    @FindBy(xpath = "//main//h1[normalize-space()='%s']")
//    public YandexElement pageTitleGenericTxt;

//    public Header(WebDriver driver) {
//        super(driver);
//    }
//
//    public Header(WebDriver driver, String url, String name) {
//        super(driver, url, name);
//    }

    public Header(WebDriver browser, String name, Component parent, String xpath) {
        super(browser, name, parent, xpath);
    }

    @Override
    public YandexElement find() {
        return null;
    }

//    @Override
//    public List<YandexElement> findAll() {
//        return null;
//    }
}
