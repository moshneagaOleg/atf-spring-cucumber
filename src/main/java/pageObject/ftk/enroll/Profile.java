package pageObject.ftk.enroll;

import core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.enroll.AbstractProfile;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Profile", url = "onboarding/profile")
public class Profile extends AbstractProfile {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public Profile(WebDriver driver) {
        super(driver);
    }

    public Profile(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void completePage() {

    }

    @Override
    public void validatePageTitle() {

    }

}
