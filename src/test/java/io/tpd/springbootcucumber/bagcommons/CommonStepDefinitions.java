package io.tpd.springbootcucumber.bagcommons;

import core.assertation.VTFAssert;
import core.element.WebTypifiedElement;
import core.factory.PageCreator;
import core.factory.PageScanner;
import core.page.AbstractPage;
import core.util.WaitUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
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

    @Autowired()
    private ScenarioContext scenarioContext;

    @Given("user open page {string}")
    public void userOpenPage(String pageName) {
        WebDriver webDriver = (WebDriver) scenarioContext.getData(PageKeys.OPEN_DRIVER);
        AbstractPage page = new PageCreator(webDriver, config.getBaseUrl()).getPage(pageName);
        page.open();
    }

    @When("user is on the {string} page")
    public void userIsOnThePage(String pageName) {
        WebDriver webDriver = (WebDriver) scenarioContext.getData(PageKeys.OPEN_DRIVER);
        AbstractPage page = new PageCreator(webDriver, config.getBaseUrl()).getPage(pageName);
        VTFAssert.assertThat(String.format("User is on the '%s' page", pageName),
                WaitUtils.waitUntilCondition(page::isCurrentPage, true, 20));
    }

    @When("user clicks on the {string} from {string} page")
    public void userClicksOnTheElement(String elementName, String pageName) {
        WebDriver webDriver = (WebDriver) scenarioContext.getData(PageKeys.OPEN_DRIVER);
        WebTypifiedElement element = PageScanner.getElementByName(webDriver, elementName, pageName);
        element.click();
    }

    @Then("user verify page title from {string} page")
    public void userVerifyPageTitleFromPagePage(String pageName) {
        WebDriver webDriver = (WebDriver) scenarioContext.getData(PageKeys.OPEN_DRIVER);
        AbstractPage page = new PageCreator(webDriver, config.getBaseUrl()).getPage(pageName);
        page.validatePageTitle();
    }
}
