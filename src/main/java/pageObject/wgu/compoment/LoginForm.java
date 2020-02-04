package pageObject.wgu.compoment;

import core.element.YandexButton;
import core.element.YandexTextInput;
import core.page.AbstractComponent;
import core.page.Module;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Name("Login form")
@FindBy(xpath = "//form")
public class LoginForm extends AbstractComponent implements Module {

    @Name("Email Input")
    @Timeout(30)
    @FindBy(xpath = "//input[@name='email' and @type='email' and @placeholder='Enter email']")
    public YandexTextInput emailInp;

    @Name("Password Input")
    @Timeout(30)
    @FindBy(xpath = "//input[@name='password' and @placeholder='Password']")
    public YandexTextInput passwordInp;

    @Name("Sing In")
    @Timeout(30)
    @FindBy(xpath = "//button[@type='submit' and contains(text(),'Sign in')]")
    public YandexButton signInBtn;

    public LoginForm() {
//        PageFactory.initElements(getDriver(), this);
    }

}
