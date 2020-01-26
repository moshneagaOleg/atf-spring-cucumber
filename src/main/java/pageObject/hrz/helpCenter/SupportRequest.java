package pageObject.hrz.helpCenter;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Support Requests", url = "help-center/support-request")
public final class SupportRequest extends AbstractPage {


    public SupportRequest(WebDriver driver) {
        super(driver);
    }

    public SupportRequest(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
