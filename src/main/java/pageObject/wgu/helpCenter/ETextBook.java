package pageObject.wgu.helpCenter;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "eTextbooks", url = "help-center/etextbooks")
public final class ETextBook extends AbstractPage {


    public ETextBook(WebDriver driver) {
        super(driver);
    }

    public ETextBook(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
