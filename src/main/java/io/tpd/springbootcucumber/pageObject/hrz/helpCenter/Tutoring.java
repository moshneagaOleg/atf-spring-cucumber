package io.tpd.springbootcucumber.pageObject.hrz.helpCenter;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.assertation.VTFAssert;
import io.tpd.springbootcucumber.core.util.WaitUtils;
import org.openqa.selenium.WebDriver;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.helpCenter.AbstractTutoring;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Tutoring", url = "help-center/tutoring")
public class Tutoring extends AbstractTutoring {

    public Tutoring(WebDriver driver) {
        super(driver);
    }

    public Tutoring(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {
        VTFAssert.assertThat("Validate page title",
                WaitUtils.waitUntilCondition(() -> gnrcPageTitle.resolveLocator("Help Center | Tutoring").isDisplayed(),
                        true, 10));
    }
}
