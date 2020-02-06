package pageObject.compoment;

import core.element.YandexButton;
import core.element.YandexLink;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Main Menu Auth")
@FindBy(xpath = "//div[contains(@id, 'my_line_navigation')]")
public class MainMenuAuth extends HtmlElement {

    @Name("Photo Button")
    @Timeout(30)
    @FindBy(xpath = "//button[div[contains(@class,'photo')]]")
    public YandexButton photoBtn;

    @Name("Generic Link")
    @Timeout(30)
    @FindBy(xpath = "//a[normalize-space()='%s']")
    public YandexLink gnrcLink;

    public void navigateStudentPortalAuthStudent() {
        photoBtn.click();
        gnrcLink.resolveLocator("Student Portal").click();
    }

    public void navigateBillingPreferencesAuthStudent() {
        photoBtn.click();
        gnrcLink.resolveLocator("Billing Preferences").click();
    }

    public void navigateAccountSettingsAuthStudent() {
        photoBtn.click();
        gnrcLink.resolveLocator("Account settings").click();
    }

    public void navigateTranscriptsAuthStudent() {
        photoBtn.click();
        gnrcLink.resolveLocator("Transcripts").click();
    }

    public void navigateSupportRequestAuthStudent() {
        photoBtn.click();
        gnrcLink.resolveLocator("Support Request").click();
    }

    public void logout() {
        photoBtn.click();
        gnrcLink.resolveLocator("Logout").click();
    }

}
