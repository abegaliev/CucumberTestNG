package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.SuiteCRMDashboardPage;
import pages.SuiteCRMLoginPage;
import utilities.Browser;
import utilities.Config;

public class UserInterfaceTest_stepDefs {

	private WebDriver driver = Browser.getDriver();
	private  SuiteCRMDashboardPage dashPage = new SuiteCRMDashboardPage();
	private SuiteCRMLoginPage loginPage = new SuiteCRMLoginPage();
	
	@Given("^I logged into suiteCRM$")
	public void i_logged_into_suiteCRM() {
		WebDriver driver = Browser.getDriver();
		driver.get(Config.getProperty("url"));
		loginPage.login();
	}

	@Then("^CRM name should be SuiteCRM$")
	public void crm_name_should_be_SuiteCRM() {
		String actualTitle = driver.getTitle();
		String expectedTitel = "Bitnami SuiteCRM";

		Assert.assertEquals(actualTitle, expectedTitel);
	}

	@Then("^All modules should be displayed$")
	public void all_modules_should_be_displayed() {
		SoftAssert soft = new SoftAssert();
		
		for (WebElement elem : dashPage.modules) {
			soft.assertTrue(elem.isDisplayed());
		}
	}

	@Then("^Log out$")
	public void log_out() {

		dashPage.logout();
		driver.quit();;

	}

}
