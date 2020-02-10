package io.tpd.springbootcucumber.pageObject.csu.enroll;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.enroll.AbstractCourses;
import io.tpd.springbootcucumber.pageObject.compoment.Footer;
import io.tpd.springbootcucumber.pageObject.compoment.Header;
import io.tpd.springbootcucumber.pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Courses", url = "onboarding/courses")
public class Courses extends AbstractCourses {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public Courses(WebDriver driver) {
        super(driver);
    }

    public Courses(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void completePage() {

    }

    @Override
    public void validatePageTitle() {

    }

}
