package pageObject.hrz.helpCenter;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Academics", url = "help-center/academics/courses")
public final class Academics extends AbstractPage {


    public Academics(WebDriver driver) {
        super(driver);
    }

    public Academics(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
