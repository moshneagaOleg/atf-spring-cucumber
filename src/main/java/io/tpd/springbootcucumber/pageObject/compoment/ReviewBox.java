package io.tpd.springbootcucumber.pageObject.compoment;

import io.tpd.springbootcucumber.core.element.WebButton;
import io.tpd.springbootcucumber.core.element.WebSelect;
import io.tpd.springbootcucumber.core.element.WebTextInput;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Review Box")
@FindBy(xpath = "//div[@id='box']")
public class ReviewBox extends HtmlElement {

    @Timeout(30)
    @FindBy(xpath = "//textarea[@id='review_review_usertext']")
    public WebTextInput yourReview;

    @Timeout(30)
    @FindBy(xpath = "//select[contains(@class, 'startYear')]")
    public WebSelect startedAtYear;

    @Timeout(30)
    @FindBy(xpath = "//select[contains(@class, 'startMonth')]")
    public WebSelect startedAtMonth;

    @Timeout(30)
    @FindBy(xpath = "//select[contains(@class, 'startDay')]")
    public WebSelect startedAtDay;

    @Timeout(30)
    @FindBy(xpath = "//input[@name='next']")
    public WebButton postButton;

}
