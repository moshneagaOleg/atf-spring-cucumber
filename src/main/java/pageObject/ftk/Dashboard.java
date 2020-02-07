package pageObject.ftk;

import core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.AbstractDashboard;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Dashboard", url = "account/dashboard")
public class Dashboard extends AbstractDashboard {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public Dashboard(WebDriver driver) {
        super(driver);
    }

    public Dashboard(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {

    }

}
