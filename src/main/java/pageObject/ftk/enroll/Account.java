package pageObject.ftk.enroll;

import core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.enroll.AbstractAccount;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Account", url = "enrollment/account")
public class Account extends AbstractAccount {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public Account(WebDriver driver) {
        super(driver);
    }

    public Account(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void completePage() {

    }

    @Override
    public void validatePageTitle() {

    }

}
