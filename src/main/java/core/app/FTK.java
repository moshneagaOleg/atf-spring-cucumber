package core.app;

import core.app.abstractApps.AbstractStudentPortal;
import org.openqa.selenium.WebDriver;
import pageObject.ftk.Dashboard;
import pageObject.ftk.HelpCenterWelcome;
import pageObject.ftk.Login;
import pageObject.ftk.MainPage;
import pageObject.ftk.helpCenter.SupportRequest;

public class FTK extends AbstractStudentPortal {

    private HelpCenterWelcome helpCenterWelcome = new HelpCenterWelcome(driver);
    private Login login = new Login(driver);
    private Dashboard dashboard = new Dashboard(driver);
    private MainPage mainPage = new MainPage(driver);
    private SupportRequest supportRequest = new SupportRequest(driver);

    public FTK(WebDriver driver) {
        super(driver);
    }

    public static FTK initApp(WebDriver driver) {
        return new FTK(driver);
    }

    public HelpCenterWelcome helpCenterWelcome() {
        return helpCenterWelcome.initOnDemand();
    }
    public Dashboard dashboard() {
        return dashboard.initOnDemand();
    }
    public MainPage mainPage() {
        return mainPage.initOnDemand();
    }

    @Override
    public SupportRequest supportRequest() { return supportRequest.initOnDemand(); }
    public Login login() { return login.initOnDemand(); }

}
