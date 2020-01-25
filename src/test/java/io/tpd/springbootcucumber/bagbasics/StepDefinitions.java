package io.tpd.springbootcucumber.bagbasics;

import core.app.abstractApps.AbstractStudentPortal;
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
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.tpd.springbootcucumber.Config;
import io.tpd.springbootcucumber.SpringBootCucumberApplication;
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

@SpringBootTest(classes = SpringBootCucumberApplication.class)
public class StepDefinitions {

    private final Logger log = LoggerFactory.getLogger(StepDefinitions.class);

    @Autowired
    private Config config;

    @Autowired
    private Environment environment;

    private WebDriver webDriver;

//    private WGU wgu;
    private AbstractStudentPortal sp;

    @Given("user open page {string}")
    public void userOpenPage(String pageName) {
        AbstractPage page = new PageCreator(webDriver, config.getBaseUrl()).getPage(pageName);
        page.open();
    }

    @When("user is on the {string} page")
    public void userIsOnThePage(String pageName) {
        AbstractPage page = new PageCreator(webDriver, config.getBaseUrl()).getPage(pageName);
        LoggingAssert.assertTrue(String.format("User is on the '%s' page", pageName),
                WaitUtils.waitUntilCondition(page::isCurrentPage, true, 30));
    }

    @Then("user verify {string} message")
    public void userVerifyStringMessage(String message) {
        LoggingAssert.assertTrue(String.format("Message is present '%s'", message),
                waitForMessage(() -> sp.successMsgs, message, 15));
    }

    @When("user login on the page")
    public void userLoginOnThePage() {
        sp.login().emailInp.sendKeys(config.getBasicUser());
        sp.login().passwordInp.sendKeys(config.getCommonPassword());
        sp.login().continueBtn.click();
    }

    @And("user logOut")
    public void userLogOut() {
        sp.waitForClickable(sp.mainMenuAuth().photoBtn, 10).click();
        sp.waitForClickable(sp.mainMenuAuth().logoutLnk, 10).click();
    }

    @Before
    public void openBrowser() {
        webDriver = DriverFactory.openBrowser();
        // FIXME: 1/25/2020 by active profile init app from reflection
//        wgu = WGU.initApp(webDriver);
        sp = AbstractStudentPortal.initApp(webDriver);
    }

    @After
    public void closeBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.getName();
        }
        if (webDriver != null) {
            webDriver.close();
            webDriver.quit();
            log.info("Browser was closed");
        }
    }

    private Boolean waitForMessage(Supplier<List<YandexElement>> webElements, String msg, int secondsTimeout) {
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

}
