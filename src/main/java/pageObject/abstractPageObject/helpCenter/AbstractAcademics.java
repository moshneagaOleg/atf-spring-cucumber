package pageObject.abstractPageObject.helpCenter;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Academics", url = "help-center/academics/courses")
public abstract class AbstractAcademics extends AbstractPage {

    public AbstractAcademics(WebDriver driver) {
        super(driver);
    }

    public AbstractAcademics(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
