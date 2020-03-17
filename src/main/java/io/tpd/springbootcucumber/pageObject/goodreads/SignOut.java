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
@PageAccessor(name = "Sign Out", url = "user/sign_out")
public class SignOut extends AbstractPage {

    @Timeout(BIG_WAIT_TIMEOUT)
    @FindBy(xpath = "//p[normalize-space()='You’ve been signed out.']")
    public WebTextBlock pageTitle;

    public SignOut(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    public SignOut(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebTypifiedElement getPageTitle() {
        return pageTitle;
    }
}
