package io.tpd.springbootcucumber.pageObject.goodreads;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.element.WebTypifiedElement;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import io.tpd.springbootcucumber.pageObject.compoment.Header;
import io.tpd.springbootcucumber.pageObject.compoment.LoginForm;
import io.tpd.springbootcucumber.pageObject.compoment.SignIn;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

import static io.tpd.springbootcucumber.Config.BIG_WAIT_TIMEOUT;

@Timeout(BIG_WAIT_TIMEOUT)
@PageAccessor(name = "Home", url = "/")
public class Home extends AbstractPage {

    public Header header;
    public LoginForm loginForm;
    public SignIn signIn;

    public Home(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    public Home(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebTypifiedElement getPageTitle() {
        return loginForm.pageTitle;
    }
}
