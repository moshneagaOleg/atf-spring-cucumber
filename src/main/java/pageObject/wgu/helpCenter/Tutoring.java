package pageObject.wgu.helpCenter;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Tutoring", url = "help-center/tutoring")
public final class Tutoring extends AbstractPage {


    public Tutoring(WebDriver driver) {
        super(driver);
    }

    public Tutoring(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
