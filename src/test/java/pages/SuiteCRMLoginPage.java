package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Browser;
import utilities.Config;

public class SuiteCRMLoginPage {

	private WebDriver driver = Browser.getDriver();
	
	public SuiteCRMLoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "user_name")
	public WebElement username;
	
	@FindBy(id = "username_password")
	public WebElement password;
	
	@FindBy(name = "Login")
	public WebElement login;
	
	public void login() {
		username.sendKeys(Config.getProperty("username"));
		password.sendKeys(Config.getProperty("password"));
		login.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
