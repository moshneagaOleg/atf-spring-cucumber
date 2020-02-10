package io.tpd.springbootcucumber.pageObject.ftk.enroll;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.enroll.AbstractProgram;
import io.tpd.springbootcucumber.pageObject.compoment.Footer;
import io.tpd.springbootcucumber.pageObject.compoment.Header;
import io.tpd.springbootcucumber.pageObject.compoment.MainMenuAuth;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Program", url = "program")
public class Program extends AbstractProgram {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public Program(WebDriver driver) {
        super(driver);
    }

    public Program(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void completePage() {

    }

    @Override
    public void validatePageTitle() {

    }

}
