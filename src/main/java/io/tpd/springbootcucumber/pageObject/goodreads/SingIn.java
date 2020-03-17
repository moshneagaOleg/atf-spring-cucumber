package io.tpd.springbootcucumber.pageObject.goodreads;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.element.WebTextBlock;
import io.tpd.springbootcucumber.core.element.WebTypifiedElement;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

import static io.tpd.springbootcucumber.Config.BIG_WAIT_TIMEOUT;

@Timeout(BIG_WAIT_TIMEOUT)
@PageAccessor(name = "Sing In", url = "/user/sign_in")
public class SingIn extends AbstractPage {

    @Timeout(BIG_WAIT_TIMEOUT)
    @FindBy(xpath = "//h1[normalize-space()='Sign in to Goodreads']")
    public WebTextBlock pageTitle;

    @Timeout(BIG_WAIT_TIMEOUT)
    @FindBy(xpath = "//div[contains(@class, 'error')]")
    public WebTextBlock errorMessage;

    public SingIn(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    public SingIn(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebTypifiedElement getPageTitle() {
        return pageTitle;
    }
}
