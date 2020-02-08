package pageObject.compoment;

import core.element.WebLink;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Footer")
@FindBy(xpath = "//footer")
public class Footer extends HtmlElement {

    @Name("Generic Link")
    @Timeout(30)
    @FindBy(xpath = "//*[normalize-space()='%s']//parent::*//a[normalize-space()='%s']")
    public WebLink gnrcLink;

}
