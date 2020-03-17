package io.tpd.springbootcucumber.pageObject.compoment;

import io.tpd.springbootcucumber.core.element.WebButton;
import io.tpd.springbootcucumber.core.element.WebTextInput;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Sig in form")
@FindBy(xpath = "//form[@name='sign_in']")
public class SignIn extends HtmlElement {

    @Name("Email Input")
    @Timeout(30)
    @FindBy(xpath = "//input[@id='userSignInFormEmail']")
    public WebTextInput emailInp;

    @Name("Password Input")
    @Timeout(30)
    @FindBy(xpath = "//input[@id='user_password']")
    public WebTextInput passwordInp;

    @Name("Sing In")
    @Timeout(30)
    @FindBy(xpath = "//input[@value='Sign in']")
    public WebButton signInBtn;

}
