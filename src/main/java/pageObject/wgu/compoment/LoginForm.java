package pageObject.wgu.compoment;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Login form")
@FindBy(xpath = "//form")
public class LoginForm extends HtmlElement {

    @Name("Sing In")
    @Timeout(30)
    @FindBy(xpath = "//button[@type='submit' and contains(text(),'Sign in')]")
    public Button signInBtn;

}
