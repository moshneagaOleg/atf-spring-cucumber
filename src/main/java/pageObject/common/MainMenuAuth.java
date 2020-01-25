package pageObject.common;

import core.annotations.PageAccessor;
import core.element.YandexElement;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Main Menu Auth")
public final class MainMenuAuth extends AbstractPage {

    private final static String DIV_CONTAINS = "//div[@role='menu']/a[contains(text(),";

    @Name("Photo Button")
    @Timeout(30)
    @FindBy(xpath = "//button[div[contains(@class,'photo')]]")
    public YandexElement photoBtn;

    @Name("Student Portal")
    @Timeout(30)
    @FindBy(xpath = "//div[contains(@class,'dropdown-menu')]")
    public YandexElement userSettingsMenuWindow;

    @Name("Student Portal")
    @Timeout(30)
    @FindBy(xpath = DIV_CONTAINS + "'Student Portal')]")
    public YandexElement studentPortalLnk;

    @Name("Billing preferences")
    @Timeout(30)
    @FindBy(xpath = DIV_CONTAINS + "'Billing Preferences')]")
    public YandexElement billingPreferencesLnk;

    @Name("Account settings")
    @Timeout(30)
    @FindBy(xpath = DIV_CONTAINS + "'Account settings')]")
    public YandexElement accountSettingsLnk;

    @Name("Transcripts")
    @Timeout(30)
    @FindBy(xpath = DIV_CONTAINS + "'Transcripts')]")
    public YandexElement transcriptsLnk;

    @Name("Support Request")
    @Timeout(30)
    @FindBy(xpath = DIV_CONTAINS + "'Support Request')]")
    public YandexElement supportRequestLnk;

    @Name("Logout")
    @Timeout(30)
    @FindBy(xpath = DIV_CONTAINS + "'Logout')]")
    public YandexElement logoutLnk;

    public MainMenuAuth(WebDriver driver) {
        super(driver);
    }

    public MainMenuAuth(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
