package pageObject.csu.helpCenter;

import core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.AbstractSupportRequest;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Support Requests", url = "help-center/support-request")
public final class SupportRequest extends AbstractSupportRequest {

    public SupportRequest(WebDriver driver) {
        super(driver);
    }

    public SupportRequest(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {

    }

    @Override
    public void complete() {
        System.out.println("CSU Bla-Bla-Bla positive");
    }

    @Override
    public void completeNegative() {
        System.out.println("CSU Bla-Bla-Bla positive");
    }

}
