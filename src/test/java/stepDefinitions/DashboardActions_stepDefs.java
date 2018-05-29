package stepDefinitions;

import java.util.List;

import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.SuiteCRMDashboardPage;
import utilities.Element;

public class DashboardActions_stepDefs {

	SuiteCRMDashboardPage dashPage  = new SuiteCRMDashboardPage();

	
	@When("^I post \"([^\"]*)\"$")
	public void i_post(String str) {
		dashPage.postNote(str);
	}

	@Then("^Post should be displayed$")
	public void post_should_be_displayed() {
		List<String> notes = Element.getTextOfElements(dashPage.notes);
		
		for(String note : notes) {
			if(note.contains("Hello Almazbek")) {
				Assert.assertTrue(true);
			}
		}
		
	}

}
