package io.tpd.springbootcucumber.pageObject.wgu.enroll;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.element.WebLink;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.enroll.AbstractStart;
import io.tpd.springbootcucumber.pageObject.compoment.Footer;
import io.tpd.springbootcucumber.pageObject.compoment.Header;
import io.tpd.springbootcucumber.pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Start", url = "enrollment/start")
public class Start extends AbstractStart {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    @Name("Continue")
    @Timeout(30)
    @FindBy(xpath = "//a[normalize-space()='Continue']")
    public WebLink continueBtn;

    public Start(WebDriver driver) {
        super(driver);
    }

    public Start(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void completePage() {

    }

    @Override
    public void validatePageTitle() {

    }

}
