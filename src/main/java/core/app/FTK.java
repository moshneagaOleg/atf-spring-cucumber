package core.app;

import core.element.YandexElement;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.wgu.*;

import java.util.List;

public class FTK extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class,'toasted toasted-primary success')]")
    public List<YandexElement> successMsgs;

    private Footer footer = new Footer(driver);
    private MainMenuUnauth mainMenuUnauth = new MainMenuUnauth(driver);
    private MainMenuAuth mainMenuAuth = new MainMenuAuth(driver);
    private HelpCenterWelcome helpCenterWelcome = new HelpCenterWelcome(driver);
    private Login login = new Login(driver);
    private Dashboard dashboard = new Dashboard(driver);
    private MainPage mainPage = new MainPage(driver);

    private FTK(WebDriver driver) {
        super(driver);
        super.wait = new WebDriverWait(driver, 30);
    }

    public static FTK initApp(WebDriver driver) {
        return new FTK(driver);
    }
    public Footer footer() {
        return footer.initOnDemand();
    }
    public MainMenuUnauth mainMenuUnauth() {
        return mainMenuUnauth.initOnDemand();
    }
    public MainMenuAuth mainMenuAuth() {
        return mainMenuAuth.initOnDemand();
    }
    public HelpCenterWelcome helpCenterWelcome() {
        return helpCenterWelcome.initOnDemand();
    }
    public Login login() {
        return login.initOnDemand();
    }
    public Dashboard dashboard() {
        return dashboard.initOnDemand();
    }
    public MainPage mainPage() {
        return mainPage.initOnDemand();
    }

}
