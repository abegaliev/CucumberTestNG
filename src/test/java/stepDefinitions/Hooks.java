package stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.Browser;
import utilities.Extent;

public class Hooks {

	@Before
	public void setUp() {
		WebDriver driver = Browser.getDriver();
	}

	@After
	public void afterMethod(Scenario scenario) {

		WebDriver driver = Browser.getDriver();

		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "Test");
			Extent.failTest(scenario);
			
		} else {
			Extent.passTest(scenario);
		}

	}

}
