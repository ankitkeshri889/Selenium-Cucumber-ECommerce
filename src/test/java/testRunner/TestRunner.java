package testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = ".//Features/"
		, glue = "stepDefinitions"
		, dryRun = false
		, plugin = {"pretty","html:test-output"}
		, monochrome = true // remove unnecessary o/p in console window
	    , tags = {"@Sanity"}
		 )
public class TestRunner {

}
