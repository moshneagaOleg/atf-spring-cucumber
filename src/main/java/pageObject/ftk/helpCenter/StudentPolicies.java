package pageObject.ftk.helpCenter;

import core.annotations.PageAccessor;
import core.assertation.VTFAssert;
import core.util.WaitUtils;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.helpCenter.AbstractStudentPolicies;
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
