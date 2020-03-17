package io.tpd.springbootcucumber.pageObject.compoment;

import io.tpd.springbootcucumber.core.element.WebLink;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Header")
@FindBy(xpath = "//header")
public class Header extends HtmlElement {

    @Name("Generic Link")
    @Timeout(30)
    @FindBy(xpath = "//input[@placeholder='Search books']")
    public WebLink searchInput;

    @Name("Generic Link")
    @Timeout(30)
    @FindBy(xpath = "//a[contains(@class, 'profileMenu')]")
    public WebLink profileMenu;

    @Name("Generic Link")
    @Timeout(30)
    @FindBy(xpath = "//div[contains(@class, 'dropdown dropdown--profileMenu')]//a[contains(text(), 'Sign out')]")
    public WebLink signOut;

}
