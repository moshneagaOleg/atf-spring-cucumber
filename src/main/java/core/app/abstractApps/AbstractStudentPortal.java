package core.app.abstractApps;

import core.element.YandexElement;
import core.page.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.abstractPageObject.AbstractLogin;
import pageObject.abstractPageObject.AbstractSupportRequest;

import java.util.List;

public abstract class AbstractStudentPortal extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class,'toasted toasted-primary success')]")
    public List<YandexElement> successMsgs;

    @Getter
    public static String baseUrl;

    public AbstractStudentPortal(WebDriver driver) {
        super(driver);
        super.wait = new WebDriverWait(driver, 30);
    }

    public abstract AbstractLogin login();
    public abstract AbstractSupportRequest supportRequest();

}
