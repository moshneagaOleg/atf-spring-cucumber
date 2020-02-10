package pageObject.wgu.enroll;

import core.annotations.PageAccessor;
import core.element.WebLink;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import pageObject.abstractPageObject.enroll.AbstractStart;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Start", url = "enrollment/start")
public class Start extends AbstractStart {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    @Name("Continue")
    @Timeout(30)
    @FindBy(xpath = "//a[normalize-space()='Continue']")
    public WebLink continueBtn;

    public Start(WebDriver driver) {
        super(driver);
    }

    public Start(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {

    }

}
