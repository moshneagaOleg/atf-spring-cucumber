package io.tpd.springbootcucumber.pageObject.compoment;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@Name("Rate Book")
@FindBy(xpath = "//div[@id='rateBooksList']")
public class RateBook extends HtmlElement {

    @Name("Generic Link")
    @Timeout(30)
    @FindBy(xpath = "//div[contains(@id, 'bookBox')]")
    public List<Book> books;
}
