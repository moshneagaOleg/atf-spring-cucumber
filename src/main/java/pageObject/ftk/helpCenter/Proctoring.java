package pageObject.ftk.helpCenter;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Help Center Proctoring", url = "help-center/proctoring")
public final class Proctoring extends AbstractPage {


    public Proctoring(WebDriver driver) {
        super(driver);
    }

    public Proctoring(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
