package runners;

import org.testng.annotations.AfterClass;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.Browser;
import utilities.Extent;

@CucumberOptions(

		plugin = { "pretty", "html:target/cucumber-report", "json:target/cucumber.json" },

		features = "./src/test/resources/features", glue = "stepDefinitions", tags = "@dev", dryRun = false)

public class CucesRunner extends AbstractTestNGCucumberTests {

	@AfterClass
	public void tearDown() {
		Browser.quit();
		Extent.flushExtent();
	}

}
