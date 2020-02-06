package pageObject.abstractPageObject;

import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.LoginForm;
import pageObject.compoment.MainMenuAuth;

public abstract class AbstractLogin extends AbstractPage {

    public Header header;
    public LoginForm loginForm;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public AbstractLogin(WebDriver driver) {
        super(driver);
    }

    public AbstractLogin(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    public void login(String email, String password) {
        header.gnrcLink.resolveLocator("Log In").click();
        loginForm.emailInp.sendKeys(email);
        loginForm.passwordInp.sendKeys(password);
        loginForm.signInBtn.click();
    }

}
