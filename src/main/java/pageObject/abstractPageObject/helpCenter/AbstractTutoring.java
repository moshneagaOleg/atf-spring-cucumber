package pageObject.abstractPageObject.helpCenter;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Tutoring", url = "help-center/tutoring")
public abstract class AbstractTutoring extends AbstractPage {

    public AbstractTutoring(WebDriver driver) {
        super(driver);
    }

    public AbstractTutoring(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
