package pageObject.abstractPageObject;

import core.element.YandexButton;
import core.element.YandexTextInput;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

public abstract class AbstractSupportRequest extends AbstractPage {

    @Name("First Name")
    @Timeout(30)
    @FindBy(xpath = "//label[text()='First Name *']/following-sibling::div//input[@name='first-name']")
    public YandexTextInput firstNameInp;

    @Name("Last Name")
    @Timeout(30)
    @FindBy(xpath = "//label[text()='Last Name *']/following-sibling::div//input[@name='last-name']")
    public YandexTextInput lastNameInp;

    @Name("Request Details")
    @Timeout(30)
    @FindBy(xpath = "//label[text()='Request Details *']/following-sibling::div//textarea[@name='request-details']")
    public YandexTextInput requestDetailsInp;

    @Name("Submit")
    @Timeout(30)
    @FindBy(xpath = "//button[normalize-space()='Submit']")
    public YandexButton submitBtn;

    public AbstractSupportRequest(WebDriver driver) {
        super(driver);
    }

    public AbstractSupportRequest(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    public abstract void validatePageTitle();

    public abstract void complete();

    public abstract void completeNegative();

}
