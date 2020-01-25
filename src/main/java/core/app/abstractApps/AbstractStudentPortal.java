package core.app.abstractApps;

import core.element.YandexElement;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.common.Dashboard;
import pageObject.common.Login;
import pageObject.common.MainMenuAuth;
import pageObject.common.MainPage;

import java.util.List;

public class AbstractStudentPortal extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class,'toasted toasted-primary success')]")
    public List<YandexElement> successMsgs;

    private MainMenuAuth mainMenuAuth = new MainMenuAuth(driver);
    private Login login = new Login(driver);
    private MainPage mainPage = new MainPage(driver);
    private Dashboard dashboard = new Dashboard(driver);

    public AbstractStudentPortal(WebDriver driver) {
        super(driver);
        super.wait = new WebDriverWait(driver, 30);
    }

    public static AbstractStudentPortal initApp(WebDriver driver) {
        return new AbstractStudentPortal(driver);
    }

    public MainMenuAuth mainMenuAuth() {
        return mainMenuAuth.initOnDemand();
    }
    public Login login() { return login.initOnDemand(); }
    public MainPage mainPage() { return mainPage.initOnDemand(); }
    public Dashboard dashboard() { return dashboard.initOnDemand(); }

}
