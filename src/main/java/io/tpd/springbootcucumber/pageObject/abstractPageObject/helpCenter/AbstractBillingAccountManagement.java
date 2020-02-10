package io.tpd.springbootcucumber.pageObject.abstractPageObject.helpCenter;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Billing Account Management", url = "help-center/account/billing")
public abstract class AbstractBillingAccountManagement extends AbstractPage {

    public AbstractBillingAccountManagement(WebDriver driver) {
        super(driver);
    }

    public AbstractBillingAccountManagement(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
