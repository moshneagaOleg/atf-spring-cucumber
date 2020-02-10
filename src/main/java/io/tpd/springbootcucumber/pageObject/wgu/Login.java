package io.tpd.springbootcucumber.pageObject.wgu;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.AbstractLogin;
import io.tpd.springbootcucumber.pageObject.compoment.Footer;
import io.tpd.springbootcucumber.pageObject.compoment.Header;
import io.tpd.springbootcucumber.pageObject.compoment.LoginForm;
import io.tpd.springbootcucumber.pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Login", url = "login")
public class Login extends AbstractLogin {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public LoginForm loginForm;
    public Footer footer;

    public Login(WebDriver driver) {
        super(driver);
    }

    public Login(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {

    }

}
