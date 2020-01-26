package pageObject.hrz.helpCenter;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "System Check", url = "help-center/system-check")
public final class SystemCheck extends AbstractPage {


    public SystemCheck(WebDriver driver) {
        super(driver);
    }

    public SystemCheck(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
