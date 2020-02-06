package pageObject.ftk;

import core.annotations.PageAccessor;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
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

//    @Name("Page Title")
//    @Timeout(30)
//    @FindBy(xpath = "//h1[normalize-space()='Please sign in']")
//    public YandexElement pageTitle;
//
//    @Name("Page Title")
//    @Timeout(30)
//    @FindBy(xpath = "//div[@class='auth__logo']/img")
//    public YandexElement logoImg;
//
//    @Name("Email Input")
//    @Timeout(30)
//    @FindBy(xpath = "//input[@name='email' and @type='email' and @placeholder='Enter email']")
//    public YandexElement emailInp;
//
//    @Name("Page Title")
//    @Timeout(30)
//    @FindBy(xpath = "//input[@name='email']/following-sibling::span")
//    public YandexElement emailErrorMsg;
//
//    @Name("Password Input")
//    @Timeout(30)
//    @FindBy(xpath = "//input[@name='password' and @placeholder='Password']")
//    public YandexElement passwordInp;
//
//    @Name("Page Title")
//    @Timeout(30)
//    @FindBy(xpath = "//input[@name='password']/following-sibling::span")
//    public YandexElement passwordErrorMsg;
//
//    @Name("Sing In")
//    @Timeout(30)
//    @FindBy(xpath = "//button[@type='submit' and contains(text(),'Sign in')]")
//    public YandexElement signInBtn;
}
