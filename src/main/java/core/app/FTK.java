package core.app;

import core.element.YandexElement;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.ftk.*;
import pageObject.ftk.helpCenter.SupportRequest;

import java.util.List;

public class FTK extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class,'toasted toasted-primary success')]")
    public List<YandexElement> successMsgs;

    private HelpCenterWelcome helpCenterWelcome = new HelpCenterWelcome(driver);
    private Login login = new Login(driver);
    private Dashboard dashboard = new Dashboard(driver);
    private MainPage mainPage = new MainPage(driver);
    private SupportRequest supportRequest = new SupportRequest(driver);

    private FTK(WebDriver driver) {
        super(driver);
        super.wait = new WebDriverWait(driver, 30);
    }

    public static FTK initApp(WebDriver driver) {
        return new FTK(driver);
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

}
