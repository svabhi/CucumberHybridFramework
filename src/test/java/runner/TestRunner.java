package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/Login2.feature",
				 glue={"stepdefinitions","hooks"},
				 dryRun=true,
				 publish=true,
				 plugin={"pretty","html:target/CucumberReports/CucumberReport.html"})
public class TestRunner {

}
