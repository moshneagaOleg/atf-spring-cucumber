package io.tpd.springbootcucumber.bagbasics;

import core.app.WGU;
import core.assertation.LoggingAssert;
import core.driver.DriverFactory;
import core.element.YandexElement;
import core.factory.PageCreator;
import core.page.AbstractPage;
import core.util.WaitUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.java.Before;
import io.tpd.springbootcucumber.bagcommons.Config;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.function.Supplier;

@SpringBootTest()
public class StepDefinitions {

    private final Logger log = LoggerFactory.getLogger(StepDefinitions.class);

    @Autowired
    private Config config;

    @Autowired
    private Environment environment;

    private WebDriver webDriver;
    private WGU wgu;

    @Given("user open page {string}")
    public void userOpenPage(String pageName) {
        AbstractPage page = new PageCreator(webDriver).getPage(pageName);
        page.open();
    }

    @When("user is on the {string} page")
    public void userIsOnThePage(String pageName) {
        AbstractPage page = new PageCreator(webDriver).getPage(pageName);
        LoggingAssert.assertTrue(String.format("User is on the '%s' page", pageName),
                WaitUtils.waitUntilCondition(page::isCurrentPage, true, 30));
    }

    @Then("user verify {string} message")
    public void userVerifyStringMessage(String message) {
        LoggingAssert.assertTrue(String.format("Message is present '%s'", message),
                waitForMessage(() -> wgu.successMsgs, message, 15));
    }

    @When("user login on the page")
    public void userLoginOnThePage() {
        wgu.login().emailInp.sendKeys(config.getBasicUser());
        wgu.login().passwordInp.sendKeys(config.getCommonPassword());
        wgu.login().continueBtn.click();
    }

    @And("user logOut")
    public void userLogOut() {
        wgu.waitForClickable(wgu.mainMenuAuth().photoBtn, 10).click();
        wgu.waitForClickable(wgu.mainMenuAuth().logoutLnk, 10).click();
    }

    @Before
    public void openBrowser() {
        webDriver = DriverFactory.openBrowser();
        wgu = WGU.initApp(webDriver);
        String url = config.getUrl();
        String name = config.getName();
        String commonPassword = config.getCommonPassword();
        String basicUser = config.getBasicUser();
        System.out.println();
//        wgu = WGU.initApp(webDriver);
//        webDriver.get(config.getUrl());
    }

    public Boolean waitForMessage(Supplier<List<YandexElement>> webElements, String msg, int secondsTimeout) {
        Supplier<Boolean> messageIsPresent = () -> {
            for (WebElement webElem : webElements.get()) {
                if (StringUtils.contains(webElem.getText(), msg)) {
                    return true;
                }
            }
            return false;
        };
        return WaitUtils.waitUntilCondition(messageIsPresent, true, secondsTimeout);
    }



//    @When("user login on the page")
//    public void userLoginOnThePage() {
//        wgu.login().emailInp.sendKeys("auto+wgu-api-45778982010878@straighterline.com");
//        wgu.login().passwordInp.sendKeys("Secretpwd@1");
//        wgu.login().continueBtn.click();
//    }
//
//    @And("user logOut")
//    public void userLogOut() {
//        wgu.waitForClickable(wgu.mainMenuAuth().photoBtn, 10).click();
//        wgu.waitForClickable(wgu.mainMenuAuth().logoutLnk, 10).click();
//    }


//    @cucumber.api.java.en.When("^I put (\\d+) (\\w+) in the bag$")
//    public void i_put_something_in_the_bag(final int quantity, final String something) {
//        String url = config.getUrl();
//        String name = config.getName();
//        System.out.println("Buuuyyyyyyy");
//    }

}
