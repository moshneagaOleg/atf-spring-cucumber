package pageObject.wgu;

import core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.AbstractHome;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.MainMenuAuth;
import pageObject.compoment.Navigation;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Home", url = "/")
public class Home extends AbstractHome {

    public Header header;
    public Navigation navigation;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public Home(WebDriver driver) {
        super(driver);
    }

    public Home(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {

    }

}
