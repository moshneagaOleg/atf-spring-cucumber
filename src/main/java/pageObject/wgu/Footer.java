package pageObject.wgu;

import core.annotations.PageAccessor;
import core.element.YandexElement;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Footer")
public class Footer extends AbstractPage {

    @Name("Business")
    @Timeout(30)
    @FindBy(xpath = "//footer//div[h6[normalize-space()='Programs']]//li/a[normalize-space()='Business']")
    public YandexElement businessLnk;

    @Name("Teaching")
    @Timeout(30)
    @FindBy(xpath = "//footer//div[h6[normalize-space()='Programs']]//li/a[normalize-space()='Teaching']")
    public YandexElement teachingLnk;

    @Name("Information Technology")
    @Timeout(30)
    @FindBy(xpath = "//footer//div[h6[normalize-space()='Programs']]//li/a[normalize-space()='Information Technology']")
    public YandexElement informationTechnologyLnk;

    @Name("Health")
    @Timeout(30)
    @FindBy(xpath = "//footer//div[h6[normalize-space()='Programs']]//li/a[normalize-space()='Health']")
    public YandexElement healthLnk;

    @Name("About WGU Academy")
    @Timeout(30)
    @FindBy(xpath = "//footer//div[h6[normalize-space()='About academy']]//li/a[normalize-space()='About WGU Academy']")
    public YandexElement aboutWGUAcademyLnk;

    @Name("How It Works")
    @Timeout(30)
    @FindBy(xpath = "//footer//div[h6[normalize-space()='About academy']]//li/a[normalize-space()='How It Works']")
    public YandexElement howItWorksLnk;

    @Name("FAQ")
    @Timeout(30)
    @FindBy(xpath = "//footer//div[h6[normalize-space()='About academy']]//li/a[normalize-space()='FAQ']")
    public YandexElement faqLnk;

    @Name("Help Center Link")
    @Timeout(30)
    @FindBy(xpath = "//footer//a[normalize-space()='Help Center']")
    public YandexElement helpCenterLnk;

    @Name("Privacy Policy")
    @Timeout(30)
    @FindBy(xpath = "//footer//a[normalize-space()='Privacy Policy']")
    public YandexElement privacyPolicyLnk;

    @Name("Terms of Use")
    @Timeout(30)
    @FindBy(xpath = "//footer//a[normalize-space()='Terms of Use']")
    public YandexElement termsOfUseLnk;

    @Name("Footer Logo")
    @Timeout(30)
    @FindBy(xpath = "//footer//img[@class='logo']")
    public YandexElement footerLogoImg;

    @Name("Enrollment Counselor")
    @Timeout(30)
    @FindBy(xpath = "//footer//a[normalize-space()='Enrollment Counselor']")
    public YandexElement enrollmentCounselorLnk;

    @Name("Call")
    @Timeout(30)
    @FindBy(xpath = "//footer//a[normalize-space()='(888) 210-8880']")
    public YandexElement callLnk;

    public Footer(WebDriver driver) {
        super(driver);
    }

    public Footer(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}

