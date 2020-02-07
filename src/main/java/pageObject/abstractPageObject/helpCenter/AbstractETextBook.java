package pageObject.abstractPageObject.helpCenter;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "eTextbooks", url = "help-center/etextbooks")
public abstract class AbstractETextBook extends AbstractPage {

    public AbstractETextBook(WebDriver driver) {
        super(driver);
    }

    public AbstractETextBook(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
