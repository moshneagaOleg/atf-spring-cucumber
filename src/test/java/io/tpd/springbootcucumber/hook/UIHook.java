package io.tpd.springbootcucumber.hook;

import core.app.WGU;
import core.logger.TestLogHelper;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.tpd.springbootcucumber.Config;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;

import java.time.LocalTime;
import java.util.Arrays;

//@Component
@Getter
public class UIHook {

    @Autowired
    @Qualifier(value = "configuration")
    private Config config;

    @Autowired
    private Environment environment;

    public WebDriver webDriver;

    public WGU wgu;

    private Logger logger = LoggerFactory.getLogger(UIHook.class);

    @Before(order = -5)
    public void loggerConfiguration(Scenario scenario) {
        Config.SCENARIO_NAME = scenario.getName();
        Config.TENANT = Arrays.toString(environment.getActiveProfiles())
                .replaceAll("\\[", "").replaceAll("\\]", "");
        String scenarioNameFinal = scenario.getName().replaceAll("\\s+", "-");
        scenarioNameFinal = scenarioNameFinal.concat("-" + LocalTime.now().toSecondOfDay());
        TestLogHelper.startTestLogging(scenarioNameFinal);
        logger.info("Current testName value is : '{}'", TestLogHelper.getCurrentLogName());
    }

}
