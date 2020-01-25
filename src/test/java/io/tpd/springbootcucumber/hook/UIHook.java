package io.tpd.springbootcucumber.hook;

import core.app.WGU;
import core.logger.TestLogHelper;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.tpd.springbootcucumber.bagcommons.Config;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

//@Component
@Getter
public class UIHook {

    @Autowired
    private Config config;

    public WebDriver webDriver;

    public WGU wgu;

    private Logger logger = LoggerFactory.getLogger(UIHook.class);

//    @Before(order = -3)
//    public void setUp(Scenario scenario) {
//        String name = scenario.getName();
//    }

    @Before(order = -3)
    public void loggerConfiguration(Scenario scenario) {
        String scenarioNameFinal = scenario.getName().replaceAll("\\s+", "-");
        scenarioNameFinal = scenarioNameFinal.concat("-" + LocalTime.now().toSecondOfDay());
        TestLogHelper.startTestLogging(scenarioNameFinal);
        logger.info("Current testName value is : '{}'", TestLogHelper.getCurrentLogName());
    }

//    @Before
//    public void openBrowser() {
//
//        webDriver = DriverFactory.openBrowser();
//        System.out.println();
//        wgu = WGU.initApp(webDriver);
//        webDriver.get(config.getUrl());
//    }


}
