package io.tpd.springbootcucumber.hook;

import core.logger.TestLogHelper;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.tpd.springbootcucumber.Config;
import io.tpd.springbootcucumber.ScenarioContext;
import lombok.Getter;
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

    private Logger logger = LoggerFactory.getLogger(UIHook.class);

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

}
