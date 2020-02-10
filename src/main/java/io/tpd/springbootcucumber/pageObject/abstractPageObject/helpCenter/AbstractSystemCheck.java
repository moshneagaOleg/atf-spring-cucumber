package io.tpd.springbootcucumber.pageObject.abstractPageObject.helpCenter;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "System Check", url = "help-center/system-check")
public abstract class AbstractSystemCheck extends AbstractPage {

    public AbstractSystemCheck(WebDriver driver) {
        super(driver);
    }

    public AbstractSystemCheck(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
