package io.tpd.springbootcucumber.pageObject.abstractPageObject.helpCenter;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Credit Transfer", url = "help-center/credit-transfer")
public abstract class AbstractCreditTransfer extends AbstractPage {

    public AbstractCreditTransfer(WebDriver driver) {
        super(driver);
    }

    public AbstractCreditTransfer(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
