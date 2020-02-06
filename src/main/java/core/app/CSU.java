package core.app;

import core.app.abstractApps.AbstractStudentPortal;
import org.openqa.selenium.WebDriver;
import pageObject.csu.*;
import pageObject.csu.helpCenter.SupportRequest;

public class CSU extends AbstractStudentPortal {

    private MainMenuAuth mainMenuAuth = new MainMenuAuth(driver);
    private HelpCenterWelcome helpCenterWelcome = new HelpCenterWelcome(driver);
    private Login login = new Login(driver);
    private Dashboard dashboard = new Dashboard(driver);
    private MainPage mainPage = new MainPage(driver);
    private SupportRequest supportRequest = new SupportRequest(driver);

    public CSU(WebDriver driver) {
        super(driver);
    }

    public static CSU initApp(WebDriver driver) {
        return new CSU(driver);
    }

    public MainMenuAuth mainMenuAuth() { return mainMenuAuth.initOnDemand(); }
    public HelpCenterWelcome helpCenterWelcome() { return helpCenterWelcome.initOnDemand(); }
    public Dashboard dashboard() {
        return dashboard.initOnDemand();
    }
    public MainPage mainPage() {
        return mainPage.initOnDemand();
    }

    @Override
    public SupportRequest supportRequest() {
        return supportRequest.initOnDemand();
    }
    public Login login() { return login.initOnDemand(); }

}
