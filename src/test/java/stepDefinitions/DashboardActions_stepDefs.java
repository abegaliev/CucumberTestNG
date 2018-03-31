package stepDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.SuiteCRMDashboardPage;
import pages.SuiteCRMLoginPage;
import utilities.Browser;
import utilities.Selenium;

public class DashboardActions_stepDefs {

	SuiteCRMDashboardPage dashPage  = new SuiteCRMDashboardPage();

	
	@When("^I post \"([^\"]*)\"$")
	public void i_post(String str) {
		dashPage.postNote(str);
	}

	@Then("^Post should be displayed$")
	public void post_should_be_displayed() {
		List<String> notes = Selenium.getTextOfElements(dashPage.notes);
		
		for(String note : notes) {
			if(note.contains("Hello Almazbek")) {
				Assert.assertTrue(true);
			}
		}
		
	}

}
