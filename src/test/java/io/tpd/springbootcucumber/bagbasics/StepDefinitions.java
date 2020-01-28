package io.tpd.springbootcucumber.bagbasics;

import core.app.CSU;
import core.app.FTK;
import core.app.HRZ;
import core.app.WGU;
import core.app.abstractApps.AbstractStudentPortal;
import core.assertation.STRAssert;
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
import org.openqa.selenium.By;
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

    private WebDriver webDriver;

    @Autowired()
    private ScenarioContext scenarioContext;

    private AbstractStudentPortal sp;

    @Then("user verify {string} message")
    public void userVerifyStringMessage(String message) {
        STRAssert.assertTrue(String.format("Message is present '%s'", message),
                waitForMessage(() -> sp.successMsgs, message, 15));
    }

    @When("user login on the page")
    public void userLoginOnThePage() {
        webDriver = (WebDriver) scenarioContext.getData(PageKeys.OPEN_DRIVER);
        sp = (AbstractStudentPortal) scenarioContext.getData(PageKeys.STUDENT_PORTAL_INIT);
        sp.login().emailInp.sendKeys(config.getBasicUser());
        sp.login().passwordInp.sendKeys(config.getCommonPassword());
        sp.login().continueBtn.click();
    }

    @And("user logOut")
    public void userLogOut() {
        sp.waitForClickable(sp.mainMenuAuth().photoBtn, 10).click();
        sp.waitForClickable(sp.mainMenuAuth().logoutLnk, 10).click();
    }

    @Then("user verify {string}")
    public void userVerifyPageTitle(String pageTitle) {
        String xPath = String.format("//*[contains(text(), '%s')]", pageTitle);
        YandexElement title = new YandexElement(webDriver.findElements(By.xpath(xPath)).get(0));
        Boolean titleIsPresent = WaitUtils.waitUntilCondition(title::isPresent, true, 10);
        STRAssert.assertTrue(String.format("Page title is present '%s'", pageTitle), titleIsPresent);
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
        if (Boolean.valueOf(isPositive)) {
            switch (Config.TENANT) {
                case "wgu":
                    WGU wgu = (WGU) scenarioContext.getData(PageKeys.WGU_INIT);
                    wgu.supportRequest().complete();
                case "csu":
                    CSU csu = (CSU) scenarioContext.getData(PageKeys.CSU_INIT);
                    csu.supportRequest().complete();
                case "ftk":
                    FTK ftk = (FTK) scenarioContext.getData(PageKeys.FTK_INIT);
                    ftk.supportRequest().complete();
                case "hrz":
                    HRZ hrz = (HRZ) scenarioContext.getData(PageKeys.HRZ_INIT);
                    hrz.supportRequest().complete();
            }
        } else {
            switch (Config.TENANT) {
                case "wgu":
                    WGU wgu = (WGU) scenarioContext.getData(PageKeys.WGU_INIT);
                    wgu.supportRequest().complete();
                case "csu":
                    CSU csu = (CSU) scenarioContext.getData(PageKeys.CSU_INIT);
                    csu.supportRequest().complete();
                case "ftk":
                    FTK ftk = (FTK) scenarioContext.getData(PageKeys.FTK_INIT);
                    ftk.supportRequest().complete();
                case "hrz":
                    HRZ hrz = (HRZ) scenarioContext.getData(PageKeys.HRZ_INIT);
                    hrz.supportRequest().complete();
            }
        }
    }
}
