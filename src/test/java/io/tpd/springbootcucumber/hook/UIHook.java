package io.tpd.springbootcucumber.hook;

import core.app.CSU;
import core.app.FTK;
import core.app.HRZ;
import core.app.WGU;
import core.app.abstractApps.AbstractStudentPortal;
import core.driver.Browser;
import core.driver.DriverFactory;
import core.logger.TestLogHelper;
import io.cucumber.core.api.Scenario;
import io.cucumber.core.event.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.tpd.springbootcucumber.Config;
import io.tpd.springbootcucumber.PageKeys;
import io.tpd.springbootcucumber.ScenarioContext;
import lombok.Getter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.time.LocalTime;
import java.util.Arrays;

@Getter
public class UIHook {

    @Autowired
    private Config config;

    @Autowired
    private Environment environment;

    @Autowired()
    private ScenarioContext scenarioContext;

    private AbstractStudentPortal studentPortal;

    private Logger logger = LoggerFactory.getLogger(UIHook.class);
    private WebDriver webDriver;

    @Before(order = -5)
    public void loggerConfiguration(Scenario scenario) {
        logger.debug("Invoke before hook [order=1]");
        Config.SCENARIO_NAME = scenario.getName();
        Config.TENANT = Arrays.toString(environment.getActiveProfiles())
                .replaceAll("\\[", "")
                .replaceAll("\\]", "");
        String scenarioNameFinal = scenario.getName()
                .replaceAll("\\s+", "-")
                .concat("-" + LocalTime.now().toSecondOfDay());
        TestLogHelper.startTestLogging(scenarioNameFinal);
        logger.info("Current testName value is : '{}'", TestLogHelper.getCurrentLogName());
    }

    @Before()
    public void openBrowser() {
        webDriver = DriverFactory.openBrowser(Browser.get(config.getBrowser()));
        scenarioContext.save(PageKeys.OPEN_DRIVER, webDriver);
        switch (Config.TENANT) {
            case "wgu":
                scenarioContext.save(PageKeys.STUDENT_PORTAL_INIT, studentPortal = WGU.initApp(webDriver));
                scenarioContext.save(PageKeys.WGU_INIT, WGU.initApp(webDriver));
            case "csu":
                scenarioContext.save(PageKeys.STUDENT_PORTAL_INIT, studentPortal = CSU.initApp(webDriver));
                scenarioContext.save(PageKeys.CSU_INIT, CSU.initApp(webDriver));
            case "ftk":
                scenarioContext.save(PageKeys.STUDENT_PORTAL_INIT, studentPortal = FTK.initApp(webDriver));
                scenarioContext.save(PageKeys.FTK_INIT, FTK.initApp(webDriver));
            case "hrz":
                scenarioContext.save(PageKeys.STUDENT_PORTAL_INIT, studentPortal = HRZ.initApp(webDriver));
                scenarioContext.save(PageKeys.HRZ_INIT, HRZ.initApp(webDriver));
        }


//        scenarioContext.save(PageKeys.STUDENT_PORTAL_INIT, AbstractStudentPortal(webDriver));
    }

    @After
    public void closeBrowser(Scenario scenario) {
        if (scenario.getStatus().is(Status.FAILED)) {
            // FIXME: 1/28/2020 add file png in log
            scenario.embed(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES), "image/png");
            logger.info("Screenshot was taken");
        }
        DriverFactory.closeBrowser(webDriver);
    }

}
