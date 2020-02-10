package pageObject.abstractPageObject.enroll;

import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.MainMenuAuth;

public abstract class AbstractReview extends AbstractPage {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public AbstractReview(WebDriver driver) {
        super(driver);
    }
    public AbstractReview(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}