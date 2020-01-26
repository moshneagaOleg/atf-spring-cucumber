package pageObject.ftk.helpCenter;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Credit Transfer", url = "help-center/credit-transfer")
public final class CreditTransfer extends AbstractPage {


    public CreditTransfer(WebDriver driver) {
        super(driver);
    }

    public CreditTransfer(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
