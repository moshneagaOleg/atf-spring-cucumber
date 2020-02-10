package io.tpd.springbootcucumber.pageObject.hrz.helpCenter;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.assertation.VTFAssert;
import io.tpd.springbootcucumber.core.util.WaitUtils;
import org.openqa.selenium.WebDriver;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.helpCenter.AbstractAcademics;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Academics", url = "help-center/academics/courses")
public class Academics extends AbstractAcademics {

    public Academics(WebDriver driver) {
        super(driver);
    }

    public Academics(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {
        VTFAssert.assertThat("Validate page title",
                WaitUtils.waitUntilCondition(() -> gnrcPageTitle.resolveLocator("Help Center | Academics: Courses").isDisplayed(),
                        true, 10));
    }
}
