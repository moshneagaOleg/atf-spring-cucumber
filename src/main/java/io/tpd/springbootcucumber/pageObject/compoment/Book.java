package io.tpd.springbootcucumber.pageObject.compoment;

import io.tpd.springbootcucumber.core.element.WebCustomSelect;
import io.tpd.springbootcucumber.core.element.WebLink;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@Name("Book")
@FindBy(xpath = "//tr")
public class Book extends HtmlElement {

    @Name("Generic Link")
    @Timeout(30)
    @FindBy(xpath = "//div[@class='stars']//a")
    public List<WebLink> star;

    @Name("Generic Link")
    @Timeout(30)
    @FindBy(xpath = "//div[@class='wtrShelfMenu']")
    public WebCustomSelect select;

}
