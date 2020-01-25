package pageObject.wgu;

import core.annotations.PageAccessor;
import core.element.YandexElement;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Main Menu Unauth")
public class MainMenuUnauth extends AbstractPage {

    @Name("Log In")
    @Timeout(30)
    @FindBy(xpath = "//header//a[contains(text(),'Log In')]")
    public YandexElement logInLnk;

    @Name("REQUEST INFO")
    @Timeout(30)
    @FindBy(xpath = "//nav//div/a[span[text()='Request Info']]")
    public YandexElement requestInfoLnk;

    @Name("ENROLL NOW")
    @Timeout(30)
    @FindBy(xpath = "//nav//div/a[span[text()='Enroll now']]")
    public YandexElement enrollNowLnk;

    @Name("Live Chat")
    @Timeout(30)
    @FindBy(xpath = "//header//a[contains(text(),'Live Chat')]")
    public YandexElement liveChatLnk;

    @Name("Business")
    @Timeout(30)
    @FindBy(xpath = "//nav[@id='main_nav']//ul/li/a/span[text()='Business']")
    public YandexElement businessLnk;

    @Name("Teaching")
    @Timeout(30)
    @FindBy(xpath = "//nav[@id='main_nav']//ul/li/a/span[text()='Teaching']")
    public YandexElement teachingLnk;

    @Name("Information Technology")
    @Timeout(30)
    @FindBy(xpath = "//nav[@id='main_nav']//ul/li/a/span[text()='Information Technology']")
    public YandexElement informationTechnologyLnk;

    @Name("Health")
    @Timeout(30)
    @FindBy(xpath = "//nav[@id='main_nav']//ul/li/a/span[text()='Health']")
    public YandexElement healthLnk;

    @Name("About Academy")
    @Timeout(30)
    @FindBy(xpath = "//nav[@id='main_nav']//ul/li/a/span[text()='About Academy']")
    public YandexElement aboutAcademyLnk;

    @Name("About WGU Academy")
    @Timeout(30)
    @FindBy(xpath = "//nav[@id='main_nav']//div/li/a/span[text()='About WGU Academy']")
    public YandexElement aboutWGUAcademyLnk;

    @Name("How It Works")
    @Timeout(30)
    @FindBy(xpath = "//nav[@id='main_nav']//div/li/a/span[text()='How It Works']")
    public YandexElement howItWorksLnk;

    @Name("FAQ")
    @Timeout(30)
    @FindBy(xpath = "//nav[@id='main_nav']//div/li/a/span[normalize-space()='FAQ']")
    public YandexElement faqLnk;

    public MainMenuUnauth(WebDriver driver) {
        super(driver);
    }

    public MainMenuUnauth(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
