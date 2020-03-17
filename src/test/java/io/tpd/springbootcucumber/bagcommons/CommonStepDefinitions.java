package io.tpd.springbootcucumber.bagcommons;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.tpd.springbootcucumber.Config;
import io.tpd.springbootcucumber.PageKeys;
import io.tpd.springbootcucumber.ScenarioContext;
import io.tpd.springbootcucumber.core.element.WebTypifiedElement;
import io.tpd.springbootcucumber.core.exceptions.SLException;
import io.tpd.springbootcucumber.core.factory.PageCreator;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import static io.tpd.springbootcucumber.Config.BIG_WAIT_TIMEOUT;
import static io.tpd.springbootcucumber.core.assertation.VTFAssert.assertThat;
import static io.tpd.springbootcucumber.core.factory.PageScanner.getElementByName;
import static io.tpd.springbootcucumber.core.util.WaitUtils.waitUntilCondition;
import static java.lang.String.format;

public class CommonStepDefinitions {

    @Autowired
    private Config config;

    @Autowired
    private ScenarioContext scenarioContext;

    @Given("user opens page {string}")
    public void userOpenPage(String pageName) {
        AbstractPage page = getPage(pageName);
        scenarioContext.setCurrentPage(page);
        page.open();
    }

    @When("user is on the {string} page")
    public void userIsOnThePage(String pageName) {
        AbstractPage page = getPage(pageName);
        assertThat(format("User is on the '%s' page", pageName),
                waitUntilCondition(page::isCurrentUrl, true, BIG_WAIT_TIMEOUT));
        page.validatePageTitle();
    }

    @When("user clicks on the {string} from {string} page")
    public void userClicksOnTheElement(String elementName, String pageName) {
        getElement(elementName, pageName).click();
    }

    @Then("user verify page title from {string} page")
    public void userVerifyPageTitleFromPage(String pageName) {
        getPage(pageName).validatePageTitle();
    }

    @And("user navigates back")
    public void userNavigatesBack() {
        getDriver().navigate().back();
    }

    @And("button {string} from {string} page is not enabled")
    public void buttonFromPageIsNotEnabled(String elementName, String pageName) {
        getElement(elementName, pageName).isNotEnabledAssertion();
    }

    @And("element {string} from {string} page {word} displayed")
    public void elementFromPage(String elementName, String pageName, String word) {
        WebTypifiedElement element = getElement(elementName, pageName);
        switch (word) {
            case "is":
                element.isDisplayedAssertion();
                break;
            case "isn't":
                element.isNotDisplayedAssertion();
                break;
            default:
                throw new SLException(format("There is no option '%s'", word));
        }
    }

    @And("user refreshes current page")
    public void userRefreshesCurrentPage() {
        getDriver().navigate().refresh();
    }

    @And("user navigates {word} page")
    public void userNavigates(String word) {
        switch (word) {
            case "forward":
                getDriver().navigate().forward();
                break;
            case "previous":
                getDriver().navigate().back();
                break;
            default:
                throw new SLException(format("There is no option '%s' to navigate", word));
        }
    }

    @And("user navigates to url {string}")
    public void userNavigatesToUrl(String url) {
        getDriver().navigate().to(url);
    }

    private WebDriver getDriver() {
        return (WebDriver) scenarioContext.getData(PageKeys.OPEN_DRIVER);
    }

    private AbstractPage getPage(String pageName) {
        return new PageCreator(getDriver(), config.getBaseUrl()).getPage(pageName);
    }

    private WebTypifiedElement getElement(String elementName, String pageName) {
        return getElementByName(getDriver(), elementName, pageName);
    }

}
