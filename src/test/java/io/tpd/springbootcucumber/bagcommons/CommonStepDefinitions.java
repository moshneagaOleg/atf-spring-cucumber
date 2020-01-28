package io.tpd.springbootcucumber.bagcommons;

import core.app.abstractApps.AbstractStudentPortal;
import core.assertation.STRAssert;
import core.factory.PageCreator;
import core.page.AbstractPage;
import core.scanner.PageScanner;
import core.util.WaitUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.tpd.springbootcucumber.Config;
import io.tpd.springbootcucumber.PageKeys;
import io.tpd.springbootcucumber.ScenarioContext;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class CommonStepDefinitions {

    @Autowired
    private Config config;

    @Autowired
    private Environment environment;

    private WebDriver webDriver;

    @Autowired()
    private ScenarioContext scenarioContext;

    private AbstractStudentPortal sp;

    @Given("user open page {string}")
    public void userOpenPage(String pageName) {
        webDriver = (WebDriver) scenarioContext.getData(PageKeys.OPEN_DRIVER);
        sp = (AbstractStudentPortal) scenarioContext.getData(PageKeys.STUDENT_PORTAL_INIT);
        AbstractPage page = new PageCreator(webDriver, config.getBaseUrl()).getPage(pageName);
        page.open();
    }

    @When("user is on the {string} page")
    public void userIsOnThePage(String pageName) {
        AbstractPage page = new PageCreator(webDriver, config.getBaseUrl()).getPage(pageName);
        STRAssert.assertTrue(String.format("User is on the '%s' page", pageName),
                WaitUtils.waitUntilCondition(page::isCurrentPage, true, 5));
    }

    @When("user clicks on the {string} from {string} page")
    public void userClicksOnTheElement(String elementName, String pageName) {
        sp.waitForClickable(PageScanner.getElementByName(webDriver, elementName, pageName), 10).click();
    }

}
