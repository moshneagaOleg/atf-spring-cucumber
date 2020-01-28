package pageObject.ftk.helpCenter;

import core.annotations.PageAccessor;
import core.element.YandexElement;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Support Requests", url = "help-center/support-request")
public final class SupportRequest extends AbstractPage {

    @Name("First Name")
    @Timeout(30)
    @FindBy(xpath = "//div[contains(@class,'static-page')]//label[text()='First Name *']/following-sibling::div//input[@name='first-name']")
    public YandexElement firstNameInp;

    @Name("Last Name")
    @Timeout(30)
    @FindBy(xpath = "//div[contains(@class,'static-page')]//label[text()='Last Name *']/following-sibling::div//input[@name='last-name']")
    public YandexElement lastNameInp;

    @Name("Request Details")
    @Timeout(30)
    @FindBy(xpath = "//label[text()='Request Details *']/following-sibling::div//textarea[@name='request-details']")
    public YandexElement requestDetailsInp;

    @Name("Submit")
    @Timeout(30)
    @FindBy(xpath = "//button[normalize-space()='Submit']")
    public YandexElement submitBtn;

    public SupportRequest(WebDriver driver) {
        super(driver);
    }

    public SupportRequest(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    public void complete() {

    }

    public void completeNegative() {

    }

}
