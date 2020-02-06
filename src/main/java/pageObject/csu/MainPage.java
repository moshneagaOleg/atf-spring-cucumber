package pageObject.csu;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Main", url = "/")
public class MainPage extends AbstractPage {

    public Header header;

    public MainMenuAuth mainMenuAuth;

    public Footer footer;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
