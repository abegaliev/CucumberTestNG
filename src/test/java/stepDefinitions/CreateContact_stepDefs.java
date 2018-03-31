package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.ContactsPage;
import pages.SuiteCRMDashboardPage;
import pages.SuiteCRMLoginPage;
import utilities.Browser;
import utilities.DropDown;

public class CreateContact_stepDefs {
 
	
	protected  SuiteCRMDashboardPage dashPage = new SuiteCRMDashboardPage();;
	protected  ContactsPage contPage = new ContactsPage();
	
	@Given("^I go to create contact page$")
	public void i_go_to_create_contact_page() {
	    dashPage.goToCreateContactPage();
	}

	@Then("^I create new contact using \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"  \"([^\"]*)\"$")
	public void i_create_new_contact_using(String prefix, String firstName, String lastName, String phoneNumber, String title) {
	    contPage.createContact(firstName, lastName, prefix, title, phoneNumber);
	}
	
	@Then("^I validate new created contact info \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"  \"([^\"]*)\"$")
	public void i_validate_new_created_contact_info(String prefix, String firstName, String lastName, String phoneNumber, String title) throws Exception {
		contPage.validateContactInfo(firstName, lastName, prefix, title, phoneNumber);
	}

	
	
	
	
	
	
	
	
}
