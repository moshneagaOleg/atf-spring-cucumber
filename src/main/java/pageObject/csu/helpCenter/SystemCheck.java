package pageObject.csu.helpCenter;

import core.annotations.PageAccessor;
import core.assertation.VTFAssert;
import core.util.WaitUtils;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.helpCenter.AbstractSystemCheck;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "System Check", url = "help-center/system-check")
public class SystemCheck extends AbstractSystemCheck {

    public SystemCheck(WebDriver driver) {
        super(driver);
    }

    public SystemCheck(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {
        VTFAssert.assertThat("Validate page title",
                WaitUtils.waitUntilCondition(() -> gnrcPageTitle.resolveLocator("Help Center | System Check").isDisplayed(),
                        true, 10));
    }
}
