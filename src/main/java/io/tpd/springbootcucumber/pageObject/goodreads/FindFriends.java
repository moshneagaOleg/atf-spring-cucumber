package io.tpd.springbootcucumber.pageObject.goodreads;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.element.WebButton;
import io.tpd.springbootcucumber.core.element.WebTextBlock;
import io.tpd.springbootcucumber.core.element.WebTypifiedElement;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

import static io.tpd.springbootcucumber.Config.BIG_WAIT_TIMEOUT;

@Timeout(BIG_WAIT_TIMEOUT)
@PageAccessor(name = "Find Friends", url = "user/create_p2")
public class FindFriends extends AbstractPage {

    @Timeout(BIG_WAIT_TIMEOUT)
    @FindBy(xpath = "//h1[normalize-space()='Get book recommendations from your friends!']")
    public WebTextBlock pageTitle;

    @Timeout(BIG_WAIT_TIMEOUT)
    @Name("Skip")
    @FindBy(xpath = "//a[contains(text(), 'Skip this step')]")
    public WebButton skipButton;

    public FindFriends(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    public FindFriends(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebTypifiedElement getPageTitle() {
        return pageTitle;
    }
}
