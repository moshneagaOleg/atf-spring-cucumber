package io.tpd.springbootcucumber.pageObject.wgu.enroll;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.enroll.AbstractPayment;
import io.tpd.springbootcucumber.pageObject.compoment.Footer;
import io.tpd.springbootcucumber.pageObject.compoment.Header;
import io.tpd.springbootcucumber.pageObject.compoment.MainMenuAuth;
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
    public void completePage() {

    }

    @Override
    public void validatePageTitle() {

    }

}
