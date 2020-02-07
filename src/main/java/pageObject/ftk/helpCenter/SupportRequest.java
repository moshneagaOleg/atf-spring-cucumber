package pageObject.ftk.helpCenter;

import core.annotations.PageAccessor;
import core.assertation.VTFAssert;
import core.util.WaitUtils;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.helpCenter.AbstractSupportRequest;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Support Requests", url = "help-center/support-request")
public class SupportRequest extends AbstractSupportRequest {

    public SupportRequest(WebDriver driver) {
        super(driver);
    }

    public SupportRequest(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {
        VTFAssert.assertThat("Validate page title",
                WaitUtils.waitUntilCondition(() -> gnrcPageTitle.resolveLocator("Help Center | Support Requests").isDisplayed(),
                        true, 10));
    }

    @Override
    public void complete() {
        System.out.println("WGU Bla-Bla-Bla positive");
    }

    @Override
    public void completeNegative() {
        System.out.println("WGU Bla-Bla-Bla negative");
    }


}
