package pageObject.ftk;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Dashboard", url = "account/dashboard")
public final class Dashboard extends AbstractPage {

    public Header header;

    public MainMenuAuth mainMenuAuth;

    public Footer footer;

    public Dashboard(WebDriver driver) {
        super(driver);
    }

    public Dashboard(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
