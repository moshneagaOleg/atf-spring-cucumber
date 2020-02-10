package pageObject.csu.enroll;

import core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.enroll.AbstractPayment;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Payment", url = "enrollment/payment")
public class Payment extends AbstractPayment {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public Payment(WebDriver driver) {
        super(driver);
    }

    public Payment(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {

    }

}
