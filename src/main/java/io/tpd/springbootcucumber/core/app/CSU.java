package io.tpd.springbootcucumber.core.app;

import io.tpd.springbootcucumber.core.app.abstractApps.AbstractStudentPortal;
import org.openqa.selenium.WebDriver;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.enroll.AbstractProgram;
import io.tpd.springbootcucumber.pageObject.csu.Dashboard;
import io.tpd.springbootcucumber.pageObject.csu.Home;
import io.tpd.springbootcucumber.pageObject.csu.Login;
import io.tpd.springbootcucumber.pageObject.csu.helpCenter.*;
import io.tpd.springbootcucumber.pageObject.wgu.enroll.*;

public class CSU extends AbstractStudentPortal {

    private Home home = new Home(driver);
    private Login login = new Login(driver);
    private Dashboard dashboard = new Dashboard(driver);

    private Start start = new Start(driver);
    private Account account = new Account(driver);
    private Payment payment = new Payment(driver);
    private Review review = new Review(driver);
    private Profile profile = new Profile(driver);
    private Courses courses = new Courses(driver);
    private Success success = new Success(driver);

    private SupportRequest supportRequest = new SupportRequest(driver);
    private HelpCenterWelcome helpCenterWelcome = new HelpCenterWelcome(driver);
    private Academics academics = new Academics(driver);
    private BillingAccountManagement billingAccountManagement = new BillingAccountManagement(driver);
    private CreditTransfer creditTransfer = new CreditTransfer(driver);
    private ETextBook eTextBook = new ETextBook(driver);
    private Proctoring proctoring = new Proctoring(driver);
    private Tutoring tutoring = new Tutoring(driver);
    private StudentPolicies studentPolicies = new StudentPolicies(driver);
    private SystemCheck systemCheck = new SystemCheck(driver);

    public CSU(WebDriver driver) {
        super(driver);
    }

    public static CSU initApp(WebDriver driver) {
        return new CSU(driver);
    }

    @Override
    public Home home() { return home.initOnDemand(); }
    public Login login() { return login.initOnDemand(); }
    public Dashboard dashboard() {
        return dashboard.initOnDemand();
    }

    public Start start() { return start.initOnDemand(); }
    public AbstractProgram program() { return null; }
    public Account account() { return account.initOnDemand(); }
    public Payment payment() { return payment.initOnDemand(); }
    public Review review() { return review.initOnDemand(); }
    public Profile profile() { return profile.initOnDemand(); }
    public Courses courses() { return courses.initOnDemand(); }
    public Success success() { return success.initOnDemand(); }

    public SupportRequest supportRequest() {
        return supportRequest.initOnDemand();
    }
    public HelpCenterWelcome helpCenterWelcome() { return helpCenterWelcome.initOnDemand(); }
    public Academics academics() { return academics.initOnDemand(); }
    public BillingAccountManagement billingAccountManagement() { return billingAccountManagement.initOnDemand(); }
    public CreditTransfer creditTransfer() { return creditTransfer.initOnDemand(); }
    public ETextBook eTextBook() { return eTextBook.initOnDemand(); }
    public Proctoring proctoring() { return proctoring.initOnDemand(); }
    public Tutoring tutoring() { return tutoring.initOnDemand(); }
    public StudentPolicies studentPolicies() { return studentPolicies.initOnDemand(); }
    public SystemCheck systemCheck() { return systemCheck.initOnDemand(); }

    @Override
    public void validatePageTitle() {

    }
}
