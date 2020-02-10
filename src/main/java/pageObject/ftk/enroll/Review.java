package pageObject.ftk.enroll;

import core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.enroll.AbstractReview;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Review", url = "enrollment/review")
public class Review extends AbstractReview {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public Review(WebDriver driver) {
        super(driver);
    }

    public Review(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {

    }

}
