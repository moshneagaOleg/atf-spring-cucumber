package io.tpd.springbootcucumber.pageObject.compoment;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@Name("Search List")
@FindBy(xpath = "//table[@class='tableList']")
public class SearchList extends HtmlElement {

    public List<Book> books;

    public ReviewBox reviewBox;

}
