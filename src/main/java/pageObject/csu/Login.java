package pageObject.csu;

import core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.AbstractLogin;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.LoginForm;
import pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Login", url = "login")
public final class Login extends AbstractLogin {

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

}
