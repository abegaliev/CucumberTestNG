package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ContactsPage;
import pages.SuiteCRMDashboardPage;
import pages.SuiteCRMLoginPage;
import utilities.Browser;

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
