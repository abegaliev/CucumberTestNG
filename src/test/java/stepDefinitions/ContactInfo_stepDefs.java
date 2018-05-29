package stepDefinitions;

import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ContactsPage;

public class ContactInfo_stepDefs {
	
	protected  ContactsPage contPage = new ContactsPage();
	
	@When("^I open contact \"([^\"]*)\"$")
	public void i_open_contact(String contact) {
	  contPage.contactLinks.get(0).click();
	  
	}

	@Then("^contact name should be \"([^\"]*)\"$")
	public void contact_name_should_be(String expectedName) {
		System.out.println("Name is a "+contPage.contactName);
		System.out.println("Email adress "+contPage.contactEmail);
	   String actualName = contPage.contactName.getText();
	   Assert.assertEquals(actualName, expectedName);
	}

	@Then("^contact email should be \"([^\"]*)\"$")
	public void contact_email_should_be(String expectedEmail) {
	   String actualEmail = contPage.contactEmail.getText();
	   Assert.assertEquals(actualEmail, expectedEmail);
	}

	
	
	
	
	
	
	
	
	
	
	
}
