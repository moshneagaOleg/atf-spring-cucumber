package io.tpd.springbootcucumber.pageObject.ftk;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.AbstractDashboard;
import io.tpd.springbootcucumber.pageObject.compoment.Footer;
import io.tpd.springbootcucumber.pageObject.compoment.Header;
import io.tpd.springbootcucumber.pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Dashboard", url = "account/dashboard")
public class Dashboard extends AbstractDashboard {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public Dashboard(WebDriver driver) {
        super(driver);
    }

    public Dashboard(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {

    }

}
