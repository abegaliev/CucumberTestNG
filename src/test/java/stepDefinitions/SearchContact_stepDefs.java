package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactsPage;
import pages.SuiteCRMDashboardPage;

public class SearchContact_stepDefs  {

	private  SuiteCRMDashboardPage dashPage = new SuiteCRMDashboardPage();
	ContactsPage contPage = new ContactsPage();

	@When("^I search for \"([^\"]*)\"$")
	public void iSearchFor(String arg1) {
		// Page.waitForPageLoad(4);
		dashPage.searchFor(arg1);
	}

	@Then("^link for user \"([^\"]*)\" should be displayed$")
	public void linkForUserShouldBeDisplayed(String arg1) {
		Assert.assertTrue(!contPage.contactLinks.get(0).getAttribute("href").isEmpty(), "Link is not found.");
	}

	
	
	
	
	
	
	
	
	
	
}
