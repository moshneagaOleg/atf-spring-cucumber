package io.tpd.springbootcucumber.pageObject.compoment;

import io.tpd.springbootcucumber.core.element.WebCheckBox;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@Name("Favorite genres")
@FindBy(xpath = "//form[contains(@id, 'fav')]")
public class FavoriteGenres extends HtmlElement {

    @Name("Genre")
    @Timeout(30)
    @FindBy(xpath = "//label[contains(@class, 'genre')]")
    public List<WebCheckBox> genreCheckBoxes;

}
