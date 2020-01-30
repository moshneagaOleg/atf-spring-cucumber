package pageObject.csu;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Admin", url = "admin")
public class Admin extends AbstractPage {
    public Admin(WebDriver driver) {
        super(driver);
    }

    public Admin(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
