package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HrAppDeptEmpPage;
import tests.OracleDb;
import utilities.Browser;
import utilities.Config;
import utilities.Jsexecutor;
import utilities.Page;
import utilities.Selenium;

public class Database_verification_strepdefs {

	private WebDriver driver = Browser.getDriver();
	private Map<String, String> UIDepData;
	private Map<String, Object> DbDepData;
	
	
	@Given("^User is on DepEmpPage$")
	public void user_is_on_DepEmpPage() throws Exception {
	   driver.get(Config.getProperty("hrAppURL"));
	   
	}

	@When("^User searchs fro department id (\\d+)$")
	public void user_searchs_fro_department_id(int deptId) throws Exception {
		HrAppDeptEmpPage deptEmpPage = new HrAppDeptEmpPage();
		UIDepData = new HashMap<>();
	    
		deptEmpPage.searchForDepartment(deptId);
		UIDepData.put("DEPARTMENT_NAME", deptEmpPage.depName.getText().trim());
		UIDepData.put("MANAGER_ID", deptEmpPage.managerId.getText().trim());
		UIDepData.put("LOCATION_ID", deptEmpPage.locationId.getText().trim());
		
	}

	@Then("^User queries database with sql \"([^\"]*)\"$")
	public void user_queries_database_with_sql(String sqlQuery) throws Exception {
	   List<Map<String, Object>> DbDataList =  OracleDb.runSqlQuery(sqlQuery);
	   DbDepData = new HashMap<>();
	   
	   DbDepData = DbDataList.get(0);
	   OracleDb.closeConnection();
	    
	}

	@Then("^UI data and Database data must match$")
	public void ui_data_and_Database_data_must_match() throws Exception {

		for(String key1 : UIDepData.keySet()) {
			for(String key2: DbDepData.keySet()) {
			Assert.assertEquals(UIDepData.get(key1), String.valueOf(DbDepData.get(key2)));
			break;
		}
	}
	}
	
	@When("^User searchs for email \"([^\"]*)\" to see firstName and lastName$")
	public void user_searchs_for_email_to_see_firstName_and_lastName(String email) throws Exception {
		HrAppDeptEmpPage deptEmpPage = new HrAppDeptEmpPage();
		UIDepData = new HashMap<>();
		
		deptEmpPage.email.sendKeys(email);
		deptEmpPage.findDepails.click();
		Selenium.waitToBeVisible(deptEmpPage.firstName, 1);
		
		String firstName = deptEmpPage.firstName.getText();
		String lastName = deptEmpPage.lastName.getText();
		
		UIDepData.put("FIRST_NAME", firstName);
		UIDepData.put("LAST_NAME", lastName);
		
	}
	
	@When("^User searchs for department id (\\d+) and get number of employees$")
	public void user_searchs_for_department_id_and_get_number_of_employees(int depId) throws Exception {
		HrAppDeptEmpPage deptEmpPage = new HrAppDeptEmpPage();
		
		deptEmpPage.searchForDepartment(depId);
		Selenium.sleep(2);
		deptEmpPage.detach.click();
		Jsexecutor.scroll(1000);
		
		UIDepData = new HashMap<>();
		UIDepData.put("EMPLOYEES_COUNT", String.valueOf(deptEmpPage.employees.size()));
		
	}
	
	
	
	
}
