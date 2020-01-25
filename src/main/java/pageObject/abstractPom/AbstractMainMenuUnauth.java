package pageObject.abstractPom;

import core.element.YandexElement;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

public abstract class AbstractMainMenuUnauth extends AbstractPage {

    @Name("Log in")
    @FindBy(xpath = "//header//a[contains(text(),'Log In')]")
    public YandexElement logInLnk;

    @Name("Enroll now")
    @FindBy(xpath = "//nav//div/a[span[contains(text(), 'Enroll now')]]")
    public YandexElement enrollNowLnk;

    @Name("Live Chat")
    @FindBy(xpath = "//header//a[contains(text(),'Live Chat')]")
    public YandexElement liveChatLnk;

    public AbstractMainMenuUnauth(WebDriver driver) {
        super(driver);
    }

}
