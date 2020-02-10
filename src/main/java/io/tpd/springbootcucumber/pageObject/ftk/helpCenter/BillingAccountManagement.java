package io.tpd.springbootcucumber.pageObject.ftk.helpCenter;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.assertation.VTFAssert;
import io.tpd.springbootcucumber.core.util.WaitUtils;
import org.openqa.selenium.WebDriver;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.helpCenter.AbstractBillingAccountManagement;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Billing Account Management", url = "help-center/account/billing")
public class BillingAccountManagement extends AbstractBillingAccountManagement {

    public BillingAccountManagement(WebDriver driver) {
        super(driver);
    }

    public BillingAccountManagement(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {
        VTFAssert.assertThat("Validate page title",
                WaitUtils.waitUntilCondition(() -> gnrcPageTitle.resolveLocator("Help Center | Billing & Account Management").isDisplayed(),
                        true, 10));
    }
}
