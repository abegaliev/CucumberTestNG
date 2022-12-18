package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactsPage;

public class DuplicateConatcts_stepDef {

	ContactsPage contPage = new ContactsPage();
	
	
	@Given("^If duplicated contact \"([^\"]*)\" exists$")
	public void if_duplicated_contact_exists(String arg1) {
	   Assert.assertTrue(contPage.contactLinks.size() > 0, "There are no duplicate contacts");
	}

	@When("^remove duplicates for this contact$")
	public void remove_duplicates_for_this_contact() {
	  contPage.removeDuplicates();
	}

	@Then("^there should be only (\\d+) \"([^\"]*)\" in the contacts page$")
	public void there_should_be_only_John_Doe_in_the_contacts_page(int number, String contact) {
		Assert.assertEquals(number, (contPage.contactLinks.size()+ 1));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
