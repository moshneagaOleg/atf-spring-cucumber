package pageObject.compoment;

import core.element.WebLink;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Navigation")
@FindBy(xpath = "//nav")
public class Navigation extends HtmlElement {

    @Name("Enroll now")
    @Timeout(30)
    @FindBy(xpath = "//a[span[contains(text(), 'Enroll')]]")
    public WebLink enroll;

}
