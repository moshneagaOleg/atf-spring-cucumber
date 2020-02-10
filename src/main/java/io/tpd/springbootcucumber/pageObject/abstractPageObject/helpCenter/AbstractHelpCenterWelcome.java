package io.tpd.springbootcucumber.pageObject.abstractPageObject.helpCenter;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Help Center Welcome", url = "help-center")
public abstract class AbstractHelpCenterWelcome extends AbstractPage {

    public AbstractHelpCenterWelcome(WebDriver driver) {
        super(driver);
    }

    public AbstractHelpCenterWelcome(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
