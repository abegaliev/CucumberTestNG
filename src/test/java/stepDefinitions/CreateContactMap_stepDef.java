package stepDefinitions;

import java.util.Map;
import cucumber.api.java.en.And;
import pages.ContactsPage;
import pages.SuiteCRMDashboardPage;

public class CreateContactMap_stepDef {

	@And("^I create a new Contact$")
	public void i_create_a_new_Contact(Map<String, String> contact)  {
		SuiteCRMDashboardPage dashPage = new SuiteCRMDashboardPage();
		ContactsPage contPage = new ContactsPage();
		
		dashPage.goToCreateContactPage();
		
		if(contact.get("firstName") != null) {
			contPage.firstName.sendKeys(contact.get("firstName"));			
		}
		if(contact.get("lastName") != null) {
		contPage.lastName.sendKeys(contact.get("lastName"));
		}
		
		if(contact.get("cellPhone") != null) {
			contPage.phoneNumber.sendKeys(contact.get("cellPhone"));
		}
		
		contPage.save();
	
	}
	
	
	
	
}
