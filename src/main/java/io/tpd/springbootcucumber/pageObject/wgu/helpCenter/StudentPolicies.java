package io.tpd.springbootcucumber.pageObject.wgu.helpCenter;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.assertation.VTFAssert;
import io.tpd.springbootcucumber.core.util.WaitUtils;
import org.openqa.selenium.WebDriver;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.helpCenter.AbstractStudentPolicies;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Student Policies", url = "help-center/policies/student-policies")
public class StudentPolicies extends AbstractStudentPolicies {

    public StudentPolicies(WebDriver driver) {
        super(driver);
    }

    public StudentPolicies(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {
        VTFAssert.assertThat("Validate page title",
                WaitUtils.waitUntilCondition(() -> gnrcPageTitle.resolveLocator("Help Center | Student Policies").isDisplayed(),
                        true, 10));
    }
}
