package pageObject.csu.helpCenter;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Student Policies", url = "help-center/policies/student-policies")
public final class StudentPolicies extends AbstractPage {


    public StudentPolicies(WebDriver driver) {
        super(driver);
    }

    public StudentPolicies(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
