package io.tpd.springbootcucumber.pageObject.abstractPageObject.helpCenter;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Help Center Proctoring", url = "help-center/proctoring")
public abstract class AbstractProctoring extends AbstractPage {

    public AbstractProctoring(WebDriver driver) {
        super(driver);
    }

    public AbstractProctoring(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
