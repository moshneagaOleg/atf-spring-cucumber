package pageObject.compoment;

import core.element.WebButton;
import core.element.WebTextInput;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Login form")
@FindBy(xpath = "//form")
public class LoginForm extends HtmlElement {

    @Name("Email Input")
    @Timeout(30)
    @FindBy(xpath = "//input[@type='email']")
    public WebTextInput emailInp;

    @Name("Password Input")
    @Timeout(30)
    @FindBy(xpath = "//input[@type='password']")
    public WebTextInput passwordInp;

    @Name("Sing In")
    @Timeout(30)
    @FindBy(xpath = "//button[@type='submit']")
    public WebButton signInBtn;

}
