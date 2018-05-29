package runners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import utilities.Browser;
import utilities.Extent;

@CucumberOptions(

		plugin = { "pretty", "html:target/cucumber-report",
				"json:target/cucumber.json"},

		features = "./src/test/resources/features",
		glue = "stepDefinitions",
		tags = "@dev",
		dryRun = false
)

public class CucesRunner extends AbstractTestNGCucumberTests {

	@BeforeClass
	public void setup() {
		
	}

	@AfterClass
	public void tearDown() {
		Browser.quit();
		Extent.flushExtent();
	}

}
