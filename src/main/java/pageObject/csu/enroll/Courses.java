package pageObject.csu.enroll;

import core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.enroll.AbstractCourses;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.MainMenuAuth;
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
    public void validatePageTitle() {

    }

}
