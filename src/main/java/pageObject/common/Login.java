package pageObject.common;

import core.annotations.PageAccessor;
import core.element.YandexElement;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Login", url = "login")
public final class Login extends AbstractPage {

    @Name("Page Title")
    @Timeout(30)
    @FindBy(xpath = "//h2[contains(text(),'Registered Students')]")
    public YandexElement pageTitle;
    @Name("Email Input")
    @Timeout(30)
    @FindBy(xpath = "//div[label[text()='Email']]//input[@id='email']")
    public YandexElement emailInp;
    @Name("Email Input Error")
    @Timeout(30)
    @FindBy(xpath = "//div[label[text()='Email']]//span[contains(@class, 'error')]")
    public YandexElement emailErrorTxt;
    @Name("Password Input")
    @Timeout(30)
    @FindBy(xpath = "//div[label[text()='Password']]//input[@id='password']")
    public YandexElement passwordInp;
    @Name("Password Input Error")
    @Timeout(30)
    @FindBy(xpath = "//div[label[text()='Password']]//span[contains(@class, 'error')]")
    public YandexElement passwordErrorTxt;
    @Name("Continue Button")
    @Timeout(30)
    @FindBy(xpath = "//button[@type='submit']")
    public YandexElement continueBtn;

    public Login(WebDriver driver) {
        super(driver);
    }

    public Login(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    public void validatePageTitle(String value) {
        pageTitle.checkIfContains(value);
    }

}
