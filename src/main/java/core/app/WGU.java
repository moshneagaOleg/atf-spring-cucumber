package core.app;

import core.app.abstractApps.AbstractStudentPortal;
import org.openqa.selenium.WebDriver;
import pageObject.wgu.*;
import pageObject.wgu.helpCenter.SupportRequest;

public class WGU extends AbstractStudentPortal {

    private HelpCenterWelcome helpCenterWelcome = new HelpCenterWelcome(driver);
    private Login login = new Login(driver);
    private Dashboard dashboard = new Dashboard(driver);
    private MainPage mainPage = new MainPage(driver);
    private AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
    private SupportRequest supportRequest = new SupportRequest(driver);

    public WGU(WebDriver driver) {
        super(driver);
    }

    public static WGU initApp(WebDriver driver) {
        return new WGU(driver);
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
    public AdminLoginPage adminLoginPage() { return adminLoginPage.initOnDemand(); }

    @Override
    public SupportRequest supportRequest() { return supportRequest.initOnDemand(); }
    public Login login() { return login.initOnDemand(); }
}
