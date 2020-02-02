package pageObject.wgu;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageObject.wgu.compoment.LoginForm;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Admin Login", url = "admin/auth/login")
public class AdminLoginPage extends AbstractPage {

    public AdminLoginPage(WebDriver driver) {
        super(driver);
    }

    public AdminLoginPage(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    public LoginForm loginForm;
}
