package pageObject.abstractPom;

import core.element.YandexElement;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

public abstract class AbstractFooter extends AbstractPage {

    @Name("Footer Logo")
    @FindBy(xpath = "//footer//img[@class='logo']")
    public YandexElement footerLogoImg;

    @Name("Chat Header Label")
    @FindBy(xpath = "//div[contains(@class,'lp_maximized')]//span[text()='Contact us' or text()='Chat with us']")
    public YandexElement chatHeaderLbl;

    @Name("How It Works")
    @FindBy(xpath = "//footer//div[h6[contains(text(), 'About')]]//li/a[normalize-space()='How It Works']")
    public YandexElement howItWorksLnk;

    @Name("FAQ")
    @FindBy(xpath = "//footer//div[h6[contains(text(), 'About')]]//li/a[normalize-space()='FAQ']")
    public YandexElement faqLnk;

    @Name("Help Center")
    @FindBy(xpath = "//footer//div[h6[normalize-space()='Resources']]//li/a[normalize-space()='Help Center']")
    public YandexElement helpCenterLnk;

    @Name("Privacy Policy")
    @FindBy(xpath = "//footer//div[h6[normalize-space()='Resources']]//li/a[contains(text(), 'Privacy Policy')]")
    public YandexElement privacyPolicyLnk;

    public AbstractFooter(WebDriver driver) {
        super(driver);
    }

}
