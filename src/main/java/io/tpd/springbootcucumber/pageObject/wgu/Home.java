package io.tpd.springbootcucumber.pageObject.wgu;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.AbstractHome;
import io.tpd.springbootcucumber.pageObject.compoment.Footer;
import io.tpd.springbootcucumber.pageObject.compoment.Header;
import io.tpd.springbootcucumber.pageObject.compoment.MainMenuAuth;
import io.tpd.springbootcucumber.pageObject.compoment.Navigation;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Home", url = "/")
public class Home extends AbstractHome {

    public Header header;
    public Navigation navigation;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public Home(WebDriver driver) {
        super(driver);
    }

    public Home(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {

    }

}
