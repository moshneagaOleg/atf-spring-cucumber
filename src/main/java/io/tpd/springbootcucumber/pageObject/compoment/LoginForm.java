package io.tpd.springbootcucumber.pageObject.compoment;

import io.tpd.springbootcucumber.core.element.WebButton;
import io.tpd.springbootcucumber.core.element.WebTextInput;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Login form")
@FindBy(xpath = "//div[@id='newAccountBox']")
public class LoginForm extends HtmlElement {

    @Name("Page Title")
    @Timeout(30)
    @FindBy(xpath = "//h2[normalize-space()='New here? Create a free account!']")
    public WebTextInput pageTitle;

    @Name("Name")
    @Timeout(30)
    @FindBy(xpath = "//input[@aria-label='Name']")
    public WebTextInput name;

    @Name("Email Input")
    @Timeout(30)
    @FindBy(xpath = "//input[@id='user_email']")
    public WebTextInput emailInp;

    @Name("Password Input")
    @Timeout(30)
    @FindBy(xpath = "//input[@id='user_password_signup']")
    public WebTextInput passwordInp;

    @Name("Sing In")
    @Timeout(30)
    @FindBy(xpath = "//input[@name='next']")
    public WebButton signInBtn;

}
