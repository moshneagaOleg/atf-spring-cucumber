package pageObject.hrz.enroll;

import core.annotations.PageAccessor;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.enroll.AbstractProgram;
import pageObject.compoment.Footer;
import pageObject.compoment.Header;
import pageObject.compoment.MainMenuAuth;
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
    public void validatePageTitle() {

    }

}
