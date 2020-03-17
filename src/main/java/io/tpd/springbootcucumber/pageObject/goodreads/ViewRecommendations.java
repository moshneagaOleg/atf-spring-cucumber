package io.tpd.springbootcucumber.pageObject.goodreads;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.element.WebButton;
import io.tpd.springbootcucumber.core.element.WebTextBlock;
import io.tpd.springbootcucumber.core.element.WebTypifiedElement;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import io.tpd.springbootcucumber.pageObject.compoment.FavoriteGenres;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

import static io.tpd.springbootcucumber.Config.BIG_WAIT_TIMEOUT;

@Timeout(BIG_WAIT_TIMEOUT)
@PageAccessor(name = "View Recommendations", url = "recommendations")
public class ViewRecommendations extends AbstractPage {

    public FavoriteGenres favoriteGenres;

    @Timeout(BIG_WAIT_TIMEOUT)
    @FindBy(xpath = "//h1[normalize-space()='Recommendations']")
    public WebTextBlock pageTitle;

    @Timeout(BIG_WAIT_TIMEOUT)
    @Name("Continue")
    @FindBy(xpath = "//button[contains(text(), 'continue')]")
    public WebButton continueButton;

    public ViewRecommendations(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    public ViewRecommendations(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebTypifiedElement getPageTitle() {
        return pageTitle;
    }
}
