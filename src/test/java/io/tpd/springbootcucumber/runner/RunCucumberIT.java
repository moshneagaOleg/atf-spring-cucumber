package io.tpd.springbootcucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        stepNotifications = true,
        features = "src/test/resources/features",
        plugin = {"pretty",
                "html:target/prettyReport",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt",
                "timeline:target/timeReport"},
        glue = {"io.tpd.springbootcucumber.bagcommons",
                "io.tpd.springbootcucumber.bagbasics",
                "io.tpd.springbootcucumber.hook"},
        strict = true,
        tags = {"(@Parallel1 or @Parallel2 or @Parallel3) and (not @Ignore)"}
)
public class RunCucumberIT {
}
