package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.When;
import pages.ContactsPage;
import utilities.Browser;
import utilities.ExcelUtils;

public class CreatContactUsingExcel {

	@When("^I search for Contact reading data from Excell sheet \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_search_for_Contact_reading_data_from_Excell_sheet(String path, String sheet) {
		SearchContact_stepDefs st = new SearchContact_stepDefs();
		ContactsPage contPage = new ContactsPage();
		ExcelUtils.openExcelFile( path, sheet);

		for(int i = 1 ; i < ExcelUtils.getUsedRowsCount(); i++) {
			if(ExcelUtils.getCellData(i, 1).equals("Y")){
				st.iSearchFor(ExcelUtils.getCellData(i, 0));
				Assert.assertFalse(!contPage.contactLinks.get(0).getAttribute("href").isEmpty(), "Link is not found.");
				if(!contPage.contactLinks.get(0).getAttribute("href").isEmpty()) {
					ExcelUtils.setCellData("Passed", i, 2);
				}else {
					ExcelUtils.setCellData("Failed", i, 2);
				}
				Browser.sleep(2);
			} else {
				ExcelUtils.setCellData("Skipped", i, 2);
			}
		}
		
		
	}
	
	
}
