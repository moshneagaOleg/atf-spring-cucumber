package io.tpd.springbootcucumber.hook;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.tpd.springbootcucumber.Config;
import io.tpd.springbootcucumber.PageKeys;
import io.tpd.springbootcucumber.ScenarioContext;
import io.tpd.springbootcucumber.SpringBootCucumberApplication;
import io.tpd.springbootcucumber.app.Goodreads;
import io.tpd.springbootcucumber.core.driver.DriverFactory;
import io.tpd.springbootcucumber.core.logger.TestLogHelper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.time.LocalTime;

@Getter
@Slf4j
@SpringBootTest(classes = SpringBootCucumberApplication.class)
public class UIHook {

    private static final String CI_MODE = "ci";

    @Autowired
    private Config config;

    @Autowired
    private Environment environment;

    @Autowired
    private ScenarioContext scenarioContext;

    private Logger logger = LoggerFactory.getLogger(UIHook.class);
    private WebDriver webDriver;

    @Before(order = -5)
    public void loggerConfiguration(Scenario scenario) {
        logger.debug("Invoke before hook [order=1]");
        String scenarioNameFinal = scenario.getName()
                .replaceAll("\\s+", "-")
                .concat("-" + LocalTime.now().toSecondOfDay());
        TestLogHelper.startTestLogging(scenarioNameFinal);
        logger.info("Current testName value is : '{}'", TestLogHelper.getCurrentLogName());
    }

    @Before()
    public void openBrowser() {
        if (CI_MODE.equalsIgnoreCase(System.getProperty("runMode"))) {
            webDriver = DriverFactory.openBrowser("--no-sandbox", "headless", "--start-maximized", "--disable-notifications");
        } else {
            webDriver = DriverFactory.openBrowser("--start-maximized", "--disable-notifications");
        }
        scenarioContext.save(PageKeys.OPEN_DRIVER, webDriver);
        scenarioContext.save(PageKeys.GOODREADS_INIT, Goodreads.initApp(webDriver));
    }

    @After
    public void closeBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.embed(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES), "image/png", scenario.getName());
            logger.info("Screenshot was taken");
        }
        DriverFactory.closeBrowser(webDriver);
    }

}
