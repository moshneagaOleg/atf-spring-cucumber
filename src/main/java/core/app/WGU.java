package core.app;

import core.element.YandexElement;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.wgu.*;
import pageObject.wgu.helpCenter.SupportRequest;

import java.util.List;

public class WGU extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class,'toasted toasted-primary success')]")
    public List<YandexElement> successMsgs;

    private HelpCenterWelcome helpCenterWelcome = new HelpCenterWelcome(driver);
    private Login login = new Login(driver);
    private Dashboard dashboard = new Dashboard(driver);
    private MainPage mainPage = new MainPage(driver);
    private SupportRequest supportRequest = new SupportRequest(driver);
    private AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
//    private LoginForm loginForm = new LoginForm(driver);

    private WGU(WebDriver driver) {
        super(driver);
        super.wait = new WebDriverWait(driver, 30);
    }

    public static WGU initApp(WebDriver driver) {
        return new WGU(driver);
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
    public SupportRequest supportRequest() { return supportRequest.initOnDemand(); }
    public AdminLoginPage adminLoginPage() { return adminLoginPage.initOnDemand(); }
//    public LoginForm loginForm() { return loginForm.initOnDemand(); }
}
