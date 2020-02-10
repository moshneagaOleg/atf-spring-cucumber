package io.tpd.springbootcucumber.core.app.abstractApps;

import io.tpd.springbootcucumber.core.element.WebTypifiedElement;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.*;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.enroll.*;
import io.tpd.springbootcucumber.pageObject.abstractPageObject.helpCenter.*;

import java.util.List;

public abstract class AbstractStudentPortal extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class,'toasted toasted-primary success')]")
    public List<WebTypifiedElement> successMsgs;

    @Getter
    public static String baseUrl;

    public AbstractStudentPortal(WebDriver driver) {
        super(driver);
        super.wait = new WebDriverWait(driver, 30);
    }

    public abstract AbstractHome home();
    public abstract AbstractDashboard dashboard();
    public abstract AbstractLogin login();

    public abstract AbstractStart start();
    public abstract AbstractProgram program();
    public abstract AbstractAccount account();
    public abstract AbstractPayment payment();
    public abstract AbstractReview review();
    public abstract AbstractProfile profile();
    public abstract AbstractCourses courses();
    public abstract AbstractSuccess success();

    public abstract AbstractHelpCenterWelcome helpCenterWelcome();
    public abstract AbstractAcademics academics();
    public abstract AbstractBillingAccountManagement billingAccountManagement();
    public abstract AbstractCreditTransfer creditTransfer();
    public abstract AbstractETextBook eTextBook();
    public abstract AbstractProctoring proctoring();
    public abstract AbstractStudentPolicies studentPolicies();
    public abstract AbstractSupportRequest supportRequest();
    public abstract AbstractSystemCheck systemCheck();
    public abstract AbstractTutoring tutoring();

}
