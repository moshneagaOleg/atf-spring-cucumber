package io.tpd.springbootcucumber.bagbasics;

import core.app.abstractApps.AbstractStudentPortal;
import core.assertation.VTFAssert;
import core.element.YandexElement;
import core.util.WaitUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.tpd.springbootcucumber.Config;
import io.tpd.springbootcucumber.PageKeys;
import io.tpd.springbootcucumber.ScenarioContext;
import io.tpd.springbootcucumber.SpringBootCucumberApplication;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.function.Supplier;

@SpringBootTest(classes = SpringBootCucumberApplication.class)
public class StepDefinitions {

    @Autowired
    private Config config;

    @Autowired
    private Environment environment;

    // FIXME: 2/2/2020 remove
    private WebDriver webDriver;

    @Autowired()
    private ScenarioContext scenarioContext;

    private AbstractStudentPortal sp;

    @Then("user verify {string} message")
    public void userVerifyStringMessage(String message) {
        VTFAssert.assertThat(String.format("Message is present '%s'", message),
                waitForMessage(() -> sp.successMsgs, message, 15));
    }

    @When("user login on the page")
    public void userLoginOnThePage() {
        webDriver = (WebDriver) scenarioContext.getData(PageKeys.OPEN_DRIVER);
        sp = (AbstractStudentPortal) scenarioContext.getData(PageKeys.STUDENT_PORTAL_INIT);
        sp.login().login(config.getBasicUser(), config.getCommonPassword());
    }

    @And("user logOut")
    public void userLogOut() {
        sp.login().mainMenuAuth.logout();
    }

    @Then("user verify {string}")
    public void userVerifyPageTitle(String pageTitle) {
        String xPath = String.format("//*[contains(text(), '%s')]", pageTitle);
        // FIXME: 2/2/2020 resolve
//        YandexElement title = new YandexElement(webDriver.findElements(By.xpath(xPath)).get(0));
//        Boolean titleIsPresent = WaitUtils.waitUntilCondition(title::isPresent, true, 10);
//        VTFAssert.assertThat(String.format("Page title is present '%s'", pageTitle), titleIsPresent);
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

    @And("user complete {string} request")
    public void userCompleteRequest(String isPositive) {
        sp = (AbstractStudentPortal) scenarioContext.getData(PageKeys.STUDENT_PORTAL_INIT);

        if (Boolean.valueOf(isPositive)) {
            sp.supportRequest().complete();
        } else {
            sp.supportRequest().completeNegative();
        }
    }

    @And("user clicks submit")
    public void userClicksSubmit() {
//        WGU wgu = (WGU) scenarioContext.getData(PageKeys.WGU_INIT);
//        System.out.println();
//        wgu.adminLoginPage().loginForm.emailInp.sendKeys(RandomStringUtils.randomAlphanumeric(8));
//        wgu.adminLoginPage().loginForm.passwordInp.sendKeys(RandomStringUtils.randomAlphanumeric(8));
//        wgu.adminLoginPage().loginForm.signInBtn.click();
//        System.out.println("Clicked on button");
    }

}
