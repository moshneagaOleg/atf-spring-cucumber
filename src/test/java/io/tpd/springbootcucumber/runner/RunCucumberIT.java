package io.tpd.springbootcucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber/bagbasics",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"},
        glue = {"io.tpd.springbootcucumber.bagcommons",
                "io.tpd.springbootcucumber.bagbasics",
                "io.tpd.springbootcucumber.hook"},
        tags = {"@Login", "not @Ignore"}
)
public class RunCucumberIT {
}