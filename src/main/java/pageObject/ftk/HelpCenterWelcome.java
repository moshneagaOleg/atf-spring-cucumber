package pageObject.ftk;

import core.annotations.PageAccessor;
import core.element.YandexElement;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Help Center Welcome", url = "help-center")
public final class HelpCenterWelcome extends AbstractPage {

    @Name("Page Title")
    @Timeout(30)
    @FindBy(xpath = "//h1[normalize-space()='Help Center | Welcome']")
    public YandexElement pageTitle;

    @Name("Support Requests")
    @Timeout(30)
    @FindBy(xpath = "//a//h4[normalize-space()='Support Request']/following-sibling::" +
            "p[normalize-space()='Create a support request and get a direct response from one of our Student Advisors']")
    public YandexElement supportRequestLnk;

    @Name("Academics")
    @Timeout(30)
    @FindBy(xpath = "//a//h4[normalize-space()='Academics']/following-sibling::" +
            "p[normalize-space()='Academic resources and information to help you complete your course']")
    public YandexElement academicsLnk;

    @Name("Help Center Proctoring")
    @Timeout(30)
    @FindBy(xpath = "//a//h4[normalize-space()='Proctoring']/following-sibling::" +
            "p[normalize-space()='A complete guide for your final, proctored exam']")
    public YandexElement proctoringLnk;

    @Name("eTextbooks")
    @Timeout(30)
    @FindBy(xpath = "//a//h4[normalize-space()='eTextbooks']/following-sibling::" +
            "p[normalize-space()='Learn how to access and use your eTextbook']")
    public YandexElement eTextBooksLnk;

    @Name("Help Center Tutoring")
    @Timeout(30)
    @FindBy(xpath = "//a//h4[normalize-space()='Tutoring']/following-sibling::" +
            "p[normalize-space()='Resources and information about our on-demand tutoring services']")
    public YandexElement tutoringLnk;

    @Name("Billing Account Management")
    @Timeout(30)
    @FindBy(xpath = "//a//h4[normalize-space()='Billing & Account Management']/following-sibling::" +
            "p[normalize-space()='Resources to help manage your enrollment and account']")
    public YandexElement billingAccountManagementLnk;

    @Name("Credit Transfer")
    @Timeout(30)
    @FindBy(xpath = "//a//h4[normalize-space()='Credit Transfer']/following-sibling::" +
            "p[normalize-space()='Information about how to request and manage your transcripts']")
    public YandexElement creditTransferLnk;

    @Name("System Check")
    @Timeout(30)
    @FindBy(xpath = "//a//h4[normalize-space()='System Check']/following-sibling::" +
            "p[normalize-space()='Check your computer’s readiness to ensure you have the best experience']")
    public YandexElement systemCheckLnk;

    @Name("Student Policies")
    @Timeout(30)
    @FindBy(xpath = "//a//h4[normalize-space()='Student Policies']/following-sibling::" +
            "p[normalize-space()='Comprehensive guide to all student policies and procedures']")
    public YandexElement studentPoliciesLnk;

    public HelpCenterWelcome(WebDriver driver) {
        super(driver);
    }

    public HelpCenterWelcome(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}

