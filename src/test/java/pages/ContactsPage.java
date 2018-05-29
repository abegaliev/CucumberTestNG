package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import utilities.Browser;
import utilities.Element;

public class ContactsPage {

	private  WebDriver driver = Browser.getDriver();

	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#pagecontent tbody a[href]")
	public List<WebElement> contactLinks;

	@FindBy(css = "div[field='first_name']")
	public WebElement contactName;

	@FindBy(css = ".email-link")
	public WebElement contactEmail;

	@FindBy(css = "li[id='tab-actions']>a")
	public WebElement actionsBtn;

	@FindBy(css = "li[id='tab-actions'] input:Enabled")
	public List<WebElement> actionsOptions;

	@FindBy(id = "next_step_button")
	public WebElement nextSepBtn;

	@FindBy(css = ".list-view-rounded-corners tr[class='oddListRowS1']")
	public List<WebElement> duplicateCont;

	@FindBy(id = "perform_merge_button")
	public WebElement performMerge;

	@FindBy(css = ".tab-content #salutation")
	public WebElement prefixDropD;

	@FindBy(css = ".tab-content #first_name")
	public WebElement firstName;

	@FindBy(css = ".tab-content #last_name")
	public WebElement lastName;

	@FindBy(css = ".tab-content #title")
	public WebElement title;

	@FindBy(css = ".tab-content #phone_mobile")
	public WebElement phoneNumber;

	@FindBy(xpath = "(//input[@id='SAVE'])[1]")
	public WebElement saveBtn;

	@FindBy(css = "div[field='phone_mobile']")
	public WebElement phoneNumValid;
	
	
	public void removeDuplicates() {
		contactLinks.get(0).click();
		Element.waitToBeClickable(actionsBtn, 2).click();
		doAction("Find Duplicates");
		nextSepBtn.click();
		performMerge.click();
		if (duplicateCont.size() > 0) {
		}

	}

	public void doAction(String value) {
		for (WebElement elem : actionsOptions) {
			System.out.println("Values : " + elem.getAttribute("value"));
			if (elem.getAttribute("value").contains(value)) {
				elem.click();
			}
		}
	}

	public void createContact(String firstNameIn, String lastNameIn, String prefix, String titleIn, String phoneNum) {
		firstName.sendKeys(firstNameIn);
		lastName.sendKeys(lastNameIn);
		Select drDown = new Select(prefixDropD);
		drDown.selectByValue(prefix);
		title.sendKeys(titleIn);
		phoneNumber.sendKeys(phoneNum);
		save();
	}

	public void save() {
		saveBtn.click();
		try {
			driver.findElement(By.xpath("(//input[@title='Save'])[2]")).click();
		}catch(Exception e) {
			
		}
	}
	
	public void validateContactInfo(String firstNameIn, String lastNameIn, String prefix, String titleIn,
			String phoneNum) {

		SoftAssert soft = new SoftAssert();

		soft.assertTrue(contactName.getText().contains(lastNameIn.toUpperCase()),"Contact title validation: ");
		soft.assertEquals(firstName.getText(), firstNameIn);
		soft.assertEquals(lastName.getText(), lastNameIn);
		soft.assertEquals(phoneNumValid.getText(), phoneNum);
		soft.assertEquals(title.getText(), titleIn);

	}
	
	
	
	
	
	
	
	

}
