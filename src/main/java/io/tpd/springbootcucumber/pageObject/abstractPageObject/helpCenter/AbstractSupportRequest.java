package io.tpd.springbootcucumber.pageObject.abstractPageObject.helpCenter;

import io.tpd.springbootcucumber.core.page.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class AbstractSupportRequest extends AbstractPage {

//    @Name("First Name")
//    @Timeout(30)
//    @FindBy(xpath = "//label[text()='First Name *']/following-sibling::div//input[@name='first-name']")
//    public WebTextInput firstNameInp;
//
//    @Name("Last Name")
//    @Timeout(30)
//    @FindBy(xpath = "//label[text()='Last Name *']/following-sibling::div//input[@name='last-name']")
//    public WebTextInput lastNameInp;
//
//    @Name("Request Details")
//    @Timeout(30)
//    @FindBy(xpath = "//label[text()='Request Details *']/following-sibling::div//textarea[@name='request-details']")
//    public WebTextInput requestDetailsInp;
//
//    @Name("Submit")
//    @Timeout(30)
//    @FindBy(xpath = "//button[normalize-space()='Submit']")
//    public WebButton submitBtn;

    public AbstractSupportRequest(WebDriver driver) {
        super(driver);
    }

    public AbstractSupportRequest(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    public abstract void complete();

    public abstract void completeNegative();

}
