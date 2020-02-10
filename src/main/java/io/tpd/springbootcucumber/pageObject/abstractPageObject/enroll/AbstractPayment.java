package io.tpd.springbootcucumber.pageObject.abstractPageObject.enroll;

import io.tpd.springbootcucumber.core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import io.tpd.springbootcucumber.pageObject.compoment.Footer;
import io.tpd.springbootcucumber.pageObject.compoment.Header;
import io.tpd.springbootcucumber.pageObject.compoment.MainMenuAuth;

public abstract class AbstractPayment extends AbstractPage {

    public Header header;
    public MainMenuAuth mainMenuAuth;
    public Footer footer;

    public AbstractPayment(WebDriver driver) {
        super(driver);
    }
    public AbstractPayment(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    public abstract void completePage();

}