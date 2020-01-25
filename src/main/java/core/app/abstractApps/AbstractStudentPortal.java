package core.app.abstractApps;

import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractStudentPortal extends AbstractPage {


//    private MainMenuAuth mainMenuAuth = new MainMenuAuth(driver);
//    private Header header = new Header(driver);
//    private RegisteredStudents registeredStudents = new RegisteredStudents(driver);


    public AbstractStudentPortal(WebDriver driver) {
        super(driver);
        super.wait = new WebDriverWait(driver, 30);
    }

}
