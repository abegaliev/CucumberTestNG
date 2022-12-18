package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Browser;
import utilities.Element;
import utilities.Page;

public class SuiteCRMDashboardPage {

	private  WebDriver driver = Browser.getDriver();

	public SuiteCRMDashboardPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".nav.navbar-nav .topnav a[data-toggle='dropdown']")
	public List<WebElement> modules;

	@FindBy(xpath = "//input[contains(@title, 'Post Status Update for')]")
	public WebElement inputBox;

	@FindBy(xpath = "//div[@class='dashletNonTable']//input[@value='Post']")
	public WebElement postBtn;

	@FindBy(css = "#contentScrollerabfedfcecdaaccbaf div[style='padding:3px']")
	public List<WebElement> notes;

	@FindBy(css = "#with-label")
	public WebElement profileIcon;

	@FindBy(css = ".desktop-bar #logout_link")
	public WebElement logoutLink;

	@FindBy(xpath = "(//button[@id='searchbutton'])[3]")
	public WebElement searchBtn;

	@FindBy(xpath = "(//div[@class='input-group']//input[@id='query_string'])[5]")
	public WebElement searchField;

	@FindBy(css = ".desktop-bar li[id='quickcreatetop']")
	public WebElement createbtn;
	
	@FindBy(xpath = "//div[@class='desktop-bar']//li[@id='quickcreatetop']//a[.='Create Contact']")
	public WebElement createContact;
	
	
	public void postNote(String str) {
		inputBox.sendKeys(str);
		postBtn.click();
	}

	public void searchFor(String contact) {
		searchBtn.click();
		searchField.clear();
		searchField.sendKeys(contact, Keys.ENTER);
	}

	public void logout() {
//		Page.waitForPageLoad(2);
//		Jsexecutor.scroll(profileIcon);
		Page.moveToElement(profileIcon);
		profileIcon.click();
		Element.waitToBeVisible(logoutLink, 2).click();
	}
	
	
	public void goToCreateContactPage() {
		Page.moveToElement(createbtn);
		createContact.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
