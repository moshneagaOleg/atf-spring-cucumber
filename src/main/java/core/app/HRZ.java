package core.app;

import core.app.abstractApps.AbstractStudentPortal;
import org.openqa.selenium.WebDriver;
import pageObject.hrz.Dashboard;
import pageObject.hrz.HelpCenterWelcome;
import pageObject.hrz.Login;
import pageObject.hrz.MainPage;
import pageObject.hrz.helpCenter.SupportRequest;

public class HRZ extends AbstractStudentPortal {

    private HelpCenterWelcome helpCenterWelcome = new HelpCenterWelcome(driver);
    private Login login = new Login(driver);
    private Dashboard dashboard = new Dashboard(driver);
    private MainPage mainPage = new MainPage(driver);
    private SupportRequest supportRequest = new SupportRequest(driver);

    public HRZ(WebDriver driver) {
        super(driver);
    }

    public static HRZ initApp(WebDriver driver) {
        return new HRZ(driver);
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
