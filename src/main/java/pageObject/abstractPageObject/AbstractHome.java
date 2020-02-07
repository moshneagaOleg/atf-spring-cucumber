package pageObject.abstractPageObject;

import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.MainMenuAuth;

public abstract class AbstractHome extends AbstractPage {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public AbstractHome(WebDriver driver) {
        super(driver);
    }
    public AbstractHome(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
