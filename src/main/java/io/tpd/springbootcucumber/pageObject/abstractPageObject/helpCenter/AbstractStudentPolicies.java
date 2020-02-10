package io.tpd.springbootcucumber.pageObject.abstractPageObject.helpCenter;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Student Policies", url = "help-center/policies/student-policies")
public abstract class AbstractStudentPolicies extends AbstractPage {

    public AbstractStudentPolicies(WebDriver driver) {
        super(driver);
    }

    public AbstractStudentPolicies(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
