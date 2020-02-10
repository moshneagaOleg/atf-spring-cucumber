package io.tpd.springbootcucumber.pageObject.hrz.helpCenter;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.assertation.VTFAssert;
import io.tpd.springbootcucumber.core.util.WaitUtils;
import org.openqa.selenium.WebDriver;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.helpCenter.AbstractProctoring;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Help Center Proctoring", url = "help-center/proctoring")
public class Proctoring extends AbstractProctoring {

    public Proctoring(WebDriver driver) {
        super(driver);
    }

    public Proctoring(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {
        VTFAssert.assertThat("Validate page title",
                WaitUtils.waitUntilCondition(() -> gnrcPageTitle.resolveLocator("Help Center | Proctoring").isDisplayed(),
                        true, 10));
    }
}
