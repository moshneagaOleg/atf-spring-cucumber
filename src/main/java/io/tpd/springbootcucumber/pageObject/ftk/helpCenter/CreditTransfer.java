package io.tpd.springbootcucumber.pageObject.ftk.helpCenter;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.assertation.VTFAssert;
import io.tpd.springbootcucumber.core.util.WaitUtils;
import org.openqa.selenium.WebDriver;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.helpCenter.AbstractCreditTransfer;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Credit Transfer", url = "help-center/credit-transfer")
public class CreditTransfer extends AbstractCreditTransfer {

    public CreditTransfer(WebDriver driver) {
        super(driver);
    }

    public CreditTransfer(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {
        VTFAssert.assertThat("Validate page title",
                WaitUtils.waitUntilCondition(() -> gnrcPageTitle.resolveLocator("Help Center | Credit Transfer").isDisplayed(),
                        true, 10));
    }
}
