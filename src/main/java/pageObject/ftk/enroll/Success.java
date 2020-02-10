package pageObject.ftk.enroll;

import core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.enroll.AbstractSuccess;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Success", url = "onboarding/success")
public class Success extends AbstractSuccess {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public Success(WebDriver driver) {
        super(driver);
    }

    public Success(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {

    }

}
