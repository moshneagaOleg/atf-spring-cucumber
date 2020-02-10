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
    @FindBy(xpath = "//a[normalize-space()='%s']")
    public WebLink gnrcLink;

}
